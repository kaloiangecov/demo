/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.exceptionhandler;

import com.example.custom.exceptions.CannotBanAdminException;
import com.example.custom.exceptions.CustomError;
import com.example.custom.exceptions.MissingResourceOrBadPermission;
import com.example.custom.exceptions.UserIsBannedException;
import com.example.user.UserBean;
import java.security.Principal;
import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author kaloyan
 */
@ControllerAdvice
public class ExceptionHandlerClass {

    private CustomError customError;

    @Autowired
    private MessageSource ms;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MissingResourceOrBadPermission.class)
    public @ResponseBody
    CustomError missingResourceExceptionHandler(MissingResourceOrBadPermission e, Principal userPrincipal) {
        Date timestamp = new Date();

        customError = new CustomError();
        
        UserBean user = (UserBean) ((Authentication) userPrincipal).getPrincipal();
        
        customError.setErrorCode(e.getErrorCode());
        customError.setMessage(ms.getMessage("missing.resource.bad.permission", null, "sda", new Locale(user.getLanguage())));
        customError.setTimestamp(timestamp);

        return customError;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CannotBanAdminException.class)
    public @ResponseBody
    CustomError cannotBanAdminExceptionHandler(CannotBanAdminException e, Principal userPrincipal) {
        Date timestamp = new Date();
        
        customError = new CustomError();

        UserBean user = (UserBean) ((Authentication) userPrincipal).getPrincipal();
        
        customError.setErrorCode(e.getErrorCode());
        customError.setMessage(ms.getMessage("cannot.ban.admin", null, "sda", new Locale(user.getLanguage())));
        customError.setTimestamp(timestamp);

        return customError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserIsBannedException.class)
    public @ResponseBody
    CustomError customMessageHandler(UserIsBannedException e, Principal userPrincipal) {
        Date timestamp = new Date();
        
        customError = new CustomError();

        UserBean user = (UserBean) ((Authentication) userPrincipal).getPrincipal();
        
        customError.setErrorCode(e.getErrorCode());
        customError.setMessage(ms.getMessage("user.banned.message", null, "sda", new Locale(user.getLanguage())));
        customError.setTimestamp(timestamp);

        return customError;
    }

}
