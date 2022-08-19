package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    final AdminService adminService;
    public LoginController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("name", "Zehra Bilsin");
        return "login";
    }


    @PostMapping("/login")
    public String adminLogin(Admin admin) {
       return adminService.login(admin);
    }


}
