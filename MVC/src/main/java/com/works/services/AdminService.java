package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    final AdminRepository aRepo;
    public AdminService(AdminRepository aRepo) {
        this.aRepo = aRepo;
    }

    public String login(Admin admin) {
        Optional<Admin> optionalAdmin = aRepo.findByEmailEqualsIgnoreCaseAndPasswordEquals(admin.getEmail(), admin.getPassword());
        if ( optionalAdmin.isPresent() ) {
            System.out.println("Giriş Başarılı");
            return "login";
        }else {
            System.out.println("Kullanıcı adı yada şifre hatalı");
            return "login";
        }
    }

}
