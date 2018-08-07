package com.mitrais.khotim.utilities;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mitrais.khotim.models.User;

public class MyUtils
{
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
 
    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 
    // Get the Connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
 
    // Store user info in Session.
    public static void storeLoggedinUser(HttpSession session, User loggedinUser) {
        // On the JSP can access via ${loggedinUser}
        session.setAttribute("loggedinUser", loggedinUser);
    }
 
    // Get the user information stored in the session.
    public static User getLoggedinUser(HttpSession session) {
        User loggedinUser = (User) session.getAttribute("loggedinUser");
        return loggedinUser;
    }
 
    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieName = new Cookie(ATT_NAME_USER_NAME, user.getName());
        // 1 day (Converted to seconds)
        cookieName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieName);
    }
 
    public static String getNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 seconds (This cookie will expire immediately)
        cookieName.setMaxAge(0);
        response.addCookie(cookieName);
    }
}
