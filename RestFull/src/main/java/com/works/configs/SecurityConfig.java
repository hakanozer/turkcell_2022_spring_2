package com.works.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // sql -> username, password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    // role -> mapping
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin().disable();
    }

    /*
    ali@mail.com - 12345 - 1- customer
    veli@mail.com - 12345 - 2- product
    mehmet@mail.com - 12345 - 3- note
    zehra@mail.com - 12345 - customer,product,note
     */

}
