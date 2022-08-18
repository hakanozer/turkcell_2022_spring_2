package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Role;
import com.works.repositories.AdminRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminDetailService implements UserDetailsService {

    final AdminRepository aRepo;
    final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = aRepo.findByUsernameEqualsIgnoreCase(username);
        if (optionalAdmin.isPresent() ) {
            Admin admin = optionalAdmin.get();
            return new User(
                    admin.getUsername(),
                    admin.getPassword(),
                    admin.isEnabled(),
                    admin.isAccountNonExpired(),
                    admin.isCredentialsNonExpired(),
                    admin.isAccountNonLocked(),
                    roleParse( admin.getRoles() )
            );
        }else {
           throw new UsernameNotFoundException("Bu kullanıcı yok!");
        }
    }

    private Collection<? extends GrantedAuthority> roleParse(List<Role> roles) {
        Collection<SimpleGrantedAuthority> ls = new ArrayList<>();
        for( Role role : roles ) {
            System.out.println("role : " + role.getRole());
            ls.add( new SimpleGrantedAuthority(role.getRole()));
        }
        return ls;
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
