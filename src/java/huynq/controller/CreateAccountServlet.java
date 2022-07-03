/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.controller;

import huynq.registration.RegistrationDAO;
import huynq.registration.RegistrationInsertError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
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
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private final String CREATE_ACCOUNT_PAGE = "create_account.jsp";
    private final String LOGIN_PAGE = "login.html";
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
        String url = CREATE_ACCOUNT_PAGE;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        
        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundErr = false;
        try {
            if (username.trim().length() <= 0) {
                foundErr = true;
                errors.setUsernameLengthError("Username require not null");
            }
            if (password.trim().length() < 5) {
                foundErr = true;
                errors.setPasswordLenghtError("Password require length >= 5");
            } else if (!confirmPassword.equals(password)) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm is not matched");
            }
            if (fullname.trim().length() <= 0) {
                foundErr = true;
                errors.setFullNameLengthError("FullName require not null");
            }
            
            if (foundErr == true) {
                request.setAttribute("ERROR", errors);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(username, password, fullname, false);
                if (result == true) {
                    url = LOGIN_PAGE;
                }
            }
           
        }catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL _ " + ex.getMessage());
            if(msg.contains("duplicate")) {
                errors.setUsernameExisted(username + " existed!!!");
                request.setAttribute("ERROR", errors);
            }
        }catch (NamingException ex) {
            log("CreateAccountServlet _ Naming _ " + ex.getMessage());
            
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
