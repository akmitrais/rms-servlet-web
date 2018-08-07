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
public class DeleteAction extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Connection conn = MyUtils.getStoredConnection(request);
        User loggedinUser = (User) request.getSession().getAttribute("loggedinUser");
        int id= Integer.parseInt(request.getParameter("id"));
        String errorString = null;
        
        if (id == loggedinUser.id) {
            errorString = "Cannot delete user.";
        } else {
            try {
                DBUtils.deleteUser(conn, id);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
        
        request.setAttribute("errorString", errorString);
        
        response.sendRedirect(request.getContextPath() + "/users");
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
