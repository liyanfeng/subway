package com.subway.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
    }  
  
    @Override  
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {  
        HttpServletRequest request = (HttpServletRequest)arg0;  
        HttpServletResponse response = (HttpServletResponse)arg1;   
        HttpSession session = request.getSession();  
        String path=request.getRequestURI();
        System.out.println(path);
        if (!path.endsWith("ldenglu.html")) {
			
        if(session.getAttribute("username") == null) {  
            PrintWriter out = response.getWriter();  
            out.print("<script>window.location='/ssh/ldenglu.html' </script>");  
            out.flush();  
            out.close();  
        }  
          }
        arg2.doFilter(request, response);
    }  
  
    @Override  
    public void destroy() {  
    }  
}  
