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
public abstract class MasterException extends Exception {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public MasterException() {

    }

    public MasterException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
