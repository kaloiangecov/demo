/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.exceptions.SortTypeNotFoundException;
import com.example.exceptions.SuperSpecialSuperAwesomeMasterException;
import com.example.model.UserBean;
import com.example.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kaloyan
 */
@RestController()
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PERM_VIEW_USER')")
    public @ResponseBody List<UserBean> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/sort/{sortType}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PERM_VIEW_USER')")
    public @ResponseBody List<UserBean> getAllUsersBySort(@PathVariable("sortType") String sortType) throws SortTypeNotFoundException {
        
        if (sortType.equals("LN")) {
            return userService.getAllUsersByLastName();
        } else if (sortType.equals("DOB")) {
            return userService.getAllUsersByDateOfBirth();
        }else {
            throw new SortTypeNotFoundException("Please type a valid search type ", "166");
        }
        
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('PERM_VIEW_USER')")
    public @ResponseBody UserBean getUserByid(@PathVariable Long id) throws SuperSpecialSuperAwesomeMasterException{
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/user/remove/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('PERM_DELETE_USER')")
    public @ResponseBody UserBean deleteUser(@PathVariable("id") Long id) throws SuperSpecialSuperAwesomeMasterException{
        return userService.removeUser(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PERM_UPDATE_USER')")
    public @ResponseBody UserBean updateUser(@RequestBody @Valid UserBean user,@PathVariable Long id) throws SuperSpecialSuperAwesomeMasterException{
        return userService.updateUser(user, id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PERM_ADD_USER')")
    public @ResponseBody UserBean insertUser(@RequestBody @Valid UserBean user) throws SuperSpecialSuperAwesomeMasterException{
        return userService.insertUser(user);
    }

}
