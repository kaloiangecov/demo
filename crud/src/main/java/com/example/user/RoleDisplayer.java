/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.user;

import lombok.Data;

/**
 *
 * @author kaloyan
 */
@Data
public class RoleDisplayer {
    
    private String username;
    
    private String message;
    
    private String userRole;

    public RoleDisplayer() {
        
    }
    
}
