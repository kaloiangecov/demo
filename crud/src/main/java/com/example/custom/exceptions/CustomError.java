/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.custom.exceptions;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author kaloyan
 */
@Data
public class CustomError {

    private String message;

    private String errorCode;

    private Date timestamp;

    public CustomError() {
        
    }

    public CustomError(String message, String errorCode, Date timestamp) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }

}
