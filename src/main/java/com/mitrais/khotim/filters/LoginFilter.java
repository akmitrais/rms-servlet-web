package com.mitrais.khotim.filters;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.khotim.utilities.MyUtils;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName="loginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter
{
    /**
     * Default constructor.
     */
    public LoginFilter() {
    }
    
    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
    }
    
    // Check if the target of the request is a servlet
    private boolean isServletRequest(HttpServletRequest request) {
        // 
        // Servlet Url-pattern: /spath/*
        // 
        // => /spath
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String pathInfo = request.getPathInfo();
        
        String urlPattern = servletPath;
        
        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
        }
        
        // Key: servletName
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();
        // Collection of all servlet in your Webapp.
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        if (this.isServletRequest(req)) {
            String from = URLEncoder.encode(req.getRequestURI(), "UTF-8");
            
            if (req.getQueryString() != null) {
                from += "?" + req.getQueryString();
            }
            
            String loginURI = req.getContextPath() + "/login";
            
            if (MyUtils.getLoggedinUser(req.getSession()) != null) {
                if (req.getRequestURI().equals(loginURI)) {
                    res.sendRedirect(req.getContextPath());
                    return;
                }
                
                chain.doFilter(req, res);
                return;
            }
            
            if (req.getRequestURI().equals(loginURI)) {
                chain.doFilter(req, res);
                return;
            }
            
            res.sendRedirect(loginURI + "?from=" + from);
            return;
        }
        
        chain.doFilter(request, response);
    }
    
    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
