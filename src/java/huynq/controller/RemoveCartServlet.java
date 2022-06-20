/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.controller;

import huynq.cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huy
 */
@WebServlet(name = "RemoveCartServlet", urlPatterns = {"/RemoveCartServlet"})
public class RemoveCartServlet extends HttpServlet {
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
        try {
             // 1. Customer goes to cart place
            HttpSession session = request.getSession(false); // Session time out
            if (session != null) {
                // 2. Customer takes their cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    // 3. Customer gets items / check item existed
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        // 4. Choose all selected items
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if(selectedItems != null) {
                            // 5. Remove Selected Books from cart
                            for (String item : selectedItems) {
                                cart.removeBookFromCart(item);
                            }//end remove items form cart
                            //update session
                            session.setAttribute("CART", cart);
                        }//selected items have existed
                    } // EndIf Selected Items list is existed
                } // EndIf Items have some books
            } // EndIf existed Session
            
        }finally {
            // 6. Call View Cart function
            String urlRewriting = "DispatchController"
                                +"?btAction=View Your Cart";
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
