package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

    final HttpServletRequest req;
    public DashboardController(HttpServletRequest req) {
        this.req = req;
    }

    @GetMapping("/dashboard")
    public String dashboard( ) {
        boolean status = req.getSession().getAttribute("admin") == null;
        if (status) {
            return "redirect:/";
        }
        return "dashboard";
    }

}
