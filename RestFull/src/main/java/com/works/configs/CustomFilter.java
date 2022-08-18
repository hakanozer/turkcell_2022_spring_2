package com.works.configs;

import com.google.gson.Gson;
import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CustomFilter extends GenericFilterBean {

    final InfoRepository iRepo;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        String agent = req.getHeader("user-agent");
        String sessionID = req.getSession().getId();
        String ip = req.getRemoteAddr();
        long date = new Date().getTime();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth != null ? auth.getName() : "";
        System.out.println( name + " : " + url + " : " + agent +  " : " + sessionID +  " : " + ip + " : " +  date );

        Info i = new Info();
        i.setName(name);
        i.setUrl(url);
        i.setAgent(agent);
        i.setDate(date);
        i.setIp(ip);
        i.setSessionID(sessionID);
        iRepo.save(i);

        // fail user
        /*
        if ( !url.equals("/error") && ip.equals("0:0:0:0:0:0:0:1") ){
            res.sendRedirect("http://localhost:8090/errorx");
        }*/

        if ( ip.equals("0:0:0:0:0:0:0:2") ) {
            res.setContentType("application/json");
            res.setStatus(HttpStatus.FORBIDDEN.value());
            Map<REnum, Object> hm = new LinkedHashMap<>();
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Fail Servlet Message");

            Gson gson = new Gson();
            String obj = gson.toJson(hm);

            PrintWriter printWriter = res.getWriter();
            printWriter.print(obj);
            printWriter.flush();
        }


        chain.doFilter(req, res);

    }
}
