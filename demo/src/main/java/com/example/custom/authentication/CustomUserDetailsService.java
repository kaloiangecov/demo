/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.custom.authentication;


import com.example.model.RolePermission;
import com.example.model.UserBean;
import com.example.repository.UserRepository;
import com.example.model.UserRole;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaloyan
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserBean userBean = userRepository.findByUsername(username);
        
        if(userBean == null){
            throw new UsernameNotFoundException(username + " not found");
        }
        
//        System.out.println("............  " + userBean.getEmail() + "  ............");
//        
//        List<SimpleGrantedAuthority> authList = getAuthorities(userBean.getUserRole());
//        
//        String password = userBean.getPassword();
//        
//        CustomUserDetails userDetails = new CustomUserDetails(username, password, authList);
        
        return userBean;
    }
    
    private List<SimpleGrantedAuthority> getAuthorities(UserRole role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
    
        for (RolePermission permission : role.getRolePermissions()){
            System.out.println(permission.getName());
            authList.add(new SimpleGrantedAuthority(permission.getName()));
        }
        
        return authList;
    }
    
}
