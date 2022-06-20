/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.controller;

import huynq.cart.CartObject;
import huynq.ordered.OrderedDAO;
import huynq.ordered_detail.OrderedDetailDAO;
import huynq.product.ProductDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huy
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        double totalPrice = 0;
        String username = null;
        int order_id;
        ArrayList<String> invalid_items  = new ArrayList<>();
        try {
            //check username
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = cookies.length - 1; i >= 0; i--) {
                    if (!cookies[i].getName().equals("JSESSIONID")) {
                        username = cookies[i].getName();
                        break;
                    }
                }
            }
            if(username != null) {
                //1. customer goes to place
                HttpSession session = request.getSession(false);
                if (session != null) {
                    //2. Customer takes cart
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        // 3. Customer gets items / check item existed
                        Map<String, Integer> items = cart.getItems();
                        if (items != null) {
                            // 4. Choose all selected items
                            String[] selectedItems = request.getParameterValues("chkItem");
                            if (selectedItems != null) {
                                ProductDAO p_dao = new ProductDAO();
                                
                                for (String item : selectedItems) {
                                    //get total price = ProductDAO.getPrice() * session.quantity
                                    totalPrice += (cart.getItems().get(item) * p_dao.getProduct(item).getPrice());
                                }
                                
                                OrderedDAO dao = new OrderedDAO();
                                order_id = dao.addOrder(username,
                                                        new Timestamp(System.currentTimeMillis()),
                                                        totalPrice);

                                OrderedDetailDAO order_detail_dao = new OrderedDetailDAO();
                                boolean result = false;
                                for (String item : selectedItems) {
                                    if(!invalid_items.contains(item)) {
                                        result = order_detail_dao.addOrderDetail(item,
                                                                                cart.getItems().get(item),
                                                                                p_dao.getProduct(item).getPrice(),
                                                                                p_dao.getProduct(item).getPrice() * cart.getItems().get(item),
                                                                                order_id);
                                        if (result) {
                                            cart.removeBookFromCart(item);
                                        }
                                    }
                                }
                                //update session
                                session.setAttribute("CART", cart);
                            }
                        }
                    }
                }
            }
        } catch (NamingException e) {
        } catch (SQLException e) {
        } finally {
            String urlRewriting = "DispatchController"
                    + "?btAction=View Your Cart";
            response.sendRedirect(urlRewriting);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}