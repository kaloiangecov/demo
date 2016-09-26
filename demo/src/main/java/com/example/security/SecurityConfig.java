/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author kaloyan
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, CustomUserDetailsService customUserDetails)
//            throws Exception {
//
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
////        auth.inMemoryAuthentication().withUser("Dilyan").password("123456").roles("USER");
////        auth.inMemoryAuthentication().withUser("Kaloian").password("123456").roles("USER");
//        auth
//                .userDetailsService(customUserDetails);
//    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http
                .userDetailsService(userDetailsService())
                .authorizeRequests()
                .antMatchers("/console/**").permitAll()
//                .antMatchers("/**").access("hasRole('PERM_VIEW_USER')")
                //                .antMatchers("/**").permitAll()
                .and().httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;

    }
}
