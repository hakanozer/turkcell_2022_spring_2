package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    final AdminRepository aRepo;
    final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ResponseEntity register(Admin admin) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<Admin> optionalAdmin = aRepo.findByUsernameEqualsIgnoreCase(admin.getUsername());
        if (optionalAdmin.isPresent() ) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Bu mail adresi daha önce kullanılmış");
        }else {
            admin.setPassword( encoder.encode(admin.getPassword()) );
            aRepo.save( admin );
            hm.put(REnum.status, true);
            hm.put(REnum.result, admin);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }


}
