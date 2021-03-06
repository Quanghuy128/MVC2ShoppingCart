/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huy
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String STARTUP_SERVLET = "StartupServlet";
    private final String ADD_BOOK_TO_CART_CONTROLLER = "AddBookToCartServlet";
    private final String REMOVE_CART_CONTROLLER = "RemoveCartServlet";
    private final String LOG_OUT_CONTROLLER = "LogoutServlet";
    private final String VIEW_CART_PAGE = "viewcart.jsp";
    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
    private final String ITEM_DETAIL_PAGE = "detail.jsp";
    private final String LIST_ITEMS_VIEW_CONTROLLER = "ListItemsInViewServlet";
    private final String VIEW_CART_CONTROLLER = "ViewCartServlet";
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
        //which button did user click?
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if(button == null) {
                //do nothing
                url = STARTUP_SERVLET;
            }else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER; //map
            }else if (button.equals("Search")) {
                url = SEARCH_LASTNAME_CONTROLLER;
            }else if (button.equals("delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            }else if (button.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            }else if (button.equals("Add Book to your Cart")) {
                url = ADD_BOOK_TO_CART_CONTROLLER;
            }else if (button.equals("Remove")) {
                url = REMOVE_CART_CONTROLLER;
            }else if (button.equals("Log Out")) {
                url = LOG_OUT_CONTROLLER;
            }else if(button.equals("View Your Cart")) {
                url = VIEW_CART_PAGE;
            }else if (button.equals("Create New Account")) {
                url = CREATE_ACCOUNT_CONTROLLER;
            }else if (button.equals("CheckOut")) {
                url = CHECK_OUT_CONTROLLER;
            }else if (button.equals("ViewDetail")) {
                url = ITEM_DETAIL_PAGE;
            }else if (button.equals("ListItemsInView")) {
                url = LIST_ITEMS_VIEW_CONTROLLER;
            }else if (button.equals("ViewCart")) {
                url = VIEW_CART_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request,response);
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
