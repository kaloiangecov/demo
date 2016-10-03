/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.custom.exceptions;

/**
 *
 * @author kaloyan
 */
public class MissingResourceOrBadPermission extends MasterException{
    
    public MissingResourceOrBadPermission(){
        
    }
    
    public MissingResourceOrBadPermission(String message, String errorCode){
        super(message, errorCode);
    }
}
