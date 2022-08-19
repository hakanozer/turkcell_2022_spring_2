package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class AdminService {

    final AdminRepository aRepo;
    final HttpServletRequest req;
    public AdminService(AdminRepository aRepo, HttpServletRequest req) {
        this.aRepo = aRepo;
        this.req = req;
    }

    public String login(Admin admin) {
        Optional<Admin> optionalAdmin = aRepo.findByEmailEqualsIgnoreCaseAndPasswordEquals(admin.getEmail(), admin.getPassword());
        if ( optionalAdmin.isPresent() ) {
            Admin adm = optionalAdmin.get();
            req.getSession().setAttribute("admin", adm);
            return "redirect:/dashboard";
        }else {
            return "login";
        }
    }

}
