/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.controller;

import huynq.registration.RegistrationDAO;
import huynq.utils.DBHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author asus
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String SHOPPING_PAGE = "shopping.jsp";
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
        String url = INVALID_PAGE;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            //1. call DAO
            //new Object DAO && call method from DAO
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.checkLogin(username, password);
            
            Cookie cookie = new Cookie(username, password);
            cookie.setMaxAge(60*3);
                if (result) {
                    //2.Process
                    boolean isAdmin = dao.getRole(username, password);
                    if (isAdmin) {
                        url = SEARCH_PAGE;
                        response.addCookie(cookie);
                    }else {
                        url = SHOPPING_PAGE;
                        response.addCookie(cookie); 
                    }
                }
        } catch (NamingException ex) {
            log(ex.getMessage());
            request.setAttribute("Error", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            log(ex.getMessage());
            request.setAttribute("Error", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        } finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
