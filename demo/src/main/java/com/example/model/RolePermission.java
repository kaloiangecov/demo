/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author kaloyan
 */
@Entity
public class RolePermission implements Serializable, GrantedAuthority {
    
    @Id
    @Column(name = "PERMISSION_ID")
    private Long permissionId;
    
    @NotNull
    private String name;
    
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)  
    @JoinTable(name = "JOIN_ROLE_PERMISSION",
                joinColumns = { @JoinColumn(name="PERMISSION_ID") },
                inverseJoinColumns = { @JoinColumn(name="ROLE_ID") }) 
    private List<UserRole> roles;
    
    public RolePermission(){
        
    }
    
    public RolePermission(String name){
        this.name = name;
    }

    public RolePermission(Long permissionId, String name, List<UserRole> roles) {
        this.permissionId = permissionId;
        this.name = name;
        this.roles = roles;
    }    
    
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String getAuthority() {
        return name;
    }
    
}
