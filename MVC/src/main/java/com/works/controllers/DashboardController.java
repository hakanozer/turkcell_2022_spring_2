package com.works.controllers;

import com.works.services.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

    final HttpServletRequest req;
    final DashboardService dService;
    public DashboardController(HttpServletRequest req, DashboardService dService) {
        this.req = req;
        this.dService = dService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        boolean status = req.getSession().getAttribute("admin") == null;
        if (status) {
            return "redirect:/";
        }
        model.addAttribute("productList", dService.allProduct());
        model.addAttribute("xml", dService.xml());
        return "dashboard";
    }

}
