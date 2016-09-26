///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.example.custom.authentication;
//
//import com.example.repository.UserRepository;
//import com.example.repository.UserRoleRepository;
//import java.util.Collection;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
///**
// *
// * @author kaloyan
// */
//public class CustomUserDetails implements UserDetails {
//
//    private String username;
//    private String password;
//    private List<SimpleGrantedAuthority> authList;
//    
//    public CustomUserDetails(String username, String password,List<SimpleGrantedAuthority> authList){
//        this.username = username;
//        this.password = password;
//        this.authList = authList;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authList;
//    }
//
//}
