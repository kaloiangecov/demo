/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.exceptions.SuperSpecialSuperAwesomeMasterException;
import com.example.repository.ErrorResponse;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author kaloyan
 */
@ControllerAdvice
public class GlobalExceptionHandlerMethods {
    
    private ErrorResponse err;
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = SuperSpecialSuperAwesomeMasterException.class)
    public @ResponseBody ErrorResponse handleException(SuperSpecialSuperAwesomeMasterException e){
        err = new ErrorResponse();
        Date date= new Date();
        err.setTimestamp(date);
        err.setStatus(HttpStatus.BAD_REQUEST.toString());
        err.setMessage(e.getMessage());
        err.setErrorCode(e.getErrorCode());
        return err;
    }    
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public @ResponseBody ErrorResponse validationException(MethodArgumentNotValidException e){
        String message = "" ;
        err = new ErrorResponse();
        Date date= new Date();
        err.setTimestamp(date);
        err.setStatus(HttpStatus.BAD_REQUEST.toString());
        for(int i = 0; i < e.getBindingResult().getErrorCount(); i++){
            if(i == e.getBindingResult().getErrorCount()-1){
                message = message + e.getBindingResult().getAllErrors().get(i).getDefaultMessage() + ";";
            }else{
                message = message + e.getBindingResult().getAllErrors().get(i).getDefaultMessage() + ", ";
            }
        }
        err.setMessage(message);
        err.setErrorCode("199");
        return err;
    }
    
}
