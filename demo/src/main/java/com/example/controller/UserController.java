/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.exceptions.SortTypeNotFoundException;
import com.example.exceptions.SuperSpecialSuperAwesomeMasterException;
import com.example.repository.User;
import com.example.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public @ResponseBody List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/sort/{sortType}", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsersBySort(@PathVariable("sortType") String sortType) throws SortTypeNotFoundException {
        
        if (sortType.equals("LN")) {
            return userService.getAllUsersByLastName();
        } else if (sortType.equals("DOB")) {
            return userService.getAllUsersByDateOfBirth();
        }else {
            throw new SortTypeNotFoundException("Please type a valid search type ", "166");
        }
        
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody User getUserByid(@PathVariable Long id) throws SuperSpecialSuperAwesomeMasterException{
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/user/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody User deleteUser(@PathVariable("id") Long id) throws SuperSpecialSuperAwesomeMasterException{
        return userService.removeUser(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User updateUser(@RequestBody @Valid User user,@PathVariable Long id) throws SuperSpecialSuperAwesomeMasterException{
        return userService.updateUser(user, id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User insertUser(@RequestBody @Valid User user) throws SuperSpecialSuperAwesomeMasterException{
        return userService.insertUser(user);
    }
}
