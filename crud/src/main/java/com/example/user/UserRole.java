/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author kaloyan
 */
@Entity
@Data
public class UserRole implements GrantedAuthority{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private Long id;
    
    private String rolename;

    public UserRole(){
        
    }

    public UserRole(Long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }
    
    @Override
    public String getAuthority() {
        return rolename;
    }
    
}
