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
public class CannotBanAdminException extends MasterException {

    public CannotBanAdminException() {

    }

    public CannotBanAdminException(String message, String errorCode) {
        super(message, errorCode);
    }

}
