/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.exceptions;

/**
 *
 * @author kaloyan
 */
public class UserNotFoundException extends SuperSpecialSuperAwesomeMasterException{
    
    public UserNotFoundException(){}
    
    public UserNotFoundException(String message, String errorCode){
        super(message,errorCode);
    }

}
