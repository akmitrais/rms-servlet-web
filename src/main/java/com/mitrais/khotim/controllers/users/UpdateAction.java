package com.mitrais.khotim.controllers.users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mitrais.khotim.models.User;
import com.mitrais.khotim.utilities.DBUtils;
import com.mitrais.khotim.utilities.MyUtils;

/**
 * Servlet implementation class CreateAction
 */
public class UpdateAction extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = null;
        boolean hasError = false;
        String errorString = null;
        int id= Integer.parseInt(request.getParameter("id"));
        
        Connection conn = MyUtils.getStoredConnection(request);
        try {
            // Find the user in the DB.
            user = DBUtils.findUser(conn, id);
            
            if (user == null) {
                hasError = true;
                errorString = "User doesn't exist";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            hasError = true;
            errorString = e.getMessage();
        }
        
        request.setAttribute("errorString", errorString);
        request.setAttribute("user", user);
        
        request.getRequestDispatcher("/WEB-INF/user/form.jsp").forward(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Connection conn = MyUtils.getStoredConnection(request);
        User loggedinUser = (User) request.getSession().getAttribute("loggedinUser");
        
        int id= Integer.parseInt(request.getParameter("id"));
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String name = (String) request.getParameter("name");
        String role = (String) request.getParameter("role");
        
        User user = new User();
        user.id = id;
        user.email = email;
        user.name= name;
        user.role_id = Integer.parseInt(role);
        user.created_by = loggedinUser.id;
        user.updated_by = loggedinUser.id;
        
        if (password == null) {
            user.setPassword(user.password);
        } else {
            user.setPassword(password);
        }
        
        String errorString = null;
        
        if (email == null || name == null || role == null) {
            errorString = "Please fill all required fields";
        }
 
        if (errorString == null) {
            try {
                DBUtils.updateUser(conn, user);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
        
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("user", user);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            request.getRequestDispatcher("/WEB-INF/user/form.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/users");
        }
        
    }
}
