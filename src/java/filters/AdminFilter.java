/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AdminFilter implements Filter
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest hsr = (HttpServletRequest) request;
        HttpSession session = hsr.getSession();
        UserService us = new UserService();
        String email = (String) session.getAttribute("email");
        
        try
        {
            User user = us.get(email);
            Role role = user.getRole();

            String roleName = role.getRoleName();

            if (!roleName.contains("admin"))
            {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect("home");
                return;
            }
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
