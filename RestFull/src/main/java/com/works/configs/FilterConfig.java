package com.works.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Server UP");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        String agent = req.getHeader("user-agent");
        String sessionID = req.getSession().getId();
        String ip = req.getRemoteAddr();
        long date = new Date().getTime();
        System.out.println( url + " : " + agent +  " : " + sessionID +  " : " + ip + " : " +  date );

        // fail user
        if ( !url.equals("/error") && ip.equals("0:0:0:0:0:0:0:1") ){
            res.sendRedirect("http://localhost:8090/error");
        }


        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Serv DOWN");
    }

}
