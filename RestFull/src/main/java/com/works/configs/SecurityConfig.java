package com.works.configs;

import com.works.services.AdminDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final AdminDetailService detailService;
    final PasswordEncoder encoder;

    // sql -> username, password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService).passwordEncoder(encoder);
    }

    // role -> mapping
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/customer/**").hasRole("customer")
                .antMatchers("/product/**").hasRole("product")
                .antMatchers("/note/**").hasRole("note")
                .and()
                .csrf().disable().formLogin().disable();
        http.addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class);
        //http.csrf().disable().formLogin().disable();
    }

    /*
    ali@mail.com - 12345 - 1- customer
    veli@mail.com - 12345 - 2- product
    mehmet@mail.com - 12345 - 3- note
    zehra@mail.com - 12345 - customer,product,note
     */

}
