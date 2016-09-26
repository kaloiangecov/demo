/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

/**
 *
 * @author kaloyan
 */
@Entity
public class UserRole {

    @Id
    @Column(name = "ROLE_ID")
    private Long roleId;

    @NotNull
    private String roleName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userRole", cascade = CascadeType.REMOVE)
    private List<UserBean> users;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private List<RolePermission> rolePermissions;

    public UserRole() {

    }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public UserRole(String roleName, List<UserBean> users) {
        this.roleName = roleName;
        this.users = users;
    }

    public UserRole(Long roleId, String roleName, List<UserBean> users, List<RolePermission> rolePermissions) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
        this.rolePermissions = rolePermissions;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long id) {
        this.roleId = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

}
