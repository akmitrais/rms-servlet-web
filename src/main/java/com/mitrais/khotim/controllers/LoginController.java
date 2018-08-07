package com.mitrais.khotim.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mitrais.khotim.models.User;
import com.mitrais.khotim.utilities.DBUtils;
import com.mitrais.khotim.utilities.MyUtils;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
        
        User user = null;
        boolean hasError = false;
        String errorString = null;
        
        if (email == null || password == null || email.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required email and password!";
            System.out.println(errorString);
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                user = DBUtils.findUser(conn, email, password);
                
                if (user == null) {
                    hasError = true;
                    errorString = "Email or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        
        String from = request.getParameter("from");

        // If error, forward to /WEB-INF/login.jsp
        if (hasError) {
            user = new User();
            user.email = email;
            user.setPassword(password);
            
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
            
            request.getRequestDispatcher("/WEB-INF/login.jsp?from=" + from).forward(request, response);
        } else {
            // Store user information in Session
            HttpSession session = request.getSession();
            MyUtils.storeLoggedinUser(session, user);
            
            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            } else {
                // Deletes cookie.
                MyUtils.deleteUserCookie(response);
            }
            
            if (from != null && !from.isEmpty()) {
                response.sendRedirect(from);
            } else {
                // Redirect to dashboard page.
                response.sendRedirect(request.getContextPath() + "/home");
            }
        }
    }
    
}
