/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.user;

import com.example.custom.exceptions.MasterException;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kaloyan
 */
@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/ban/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    UserBean banUser(@PathVariable Long id, Principal userPrincipal) throws MasterException {
        return userService.banUser(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/user/unban/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    UserBean unbanUser(@PathVariable Long id, Principal userPrincipal) throws MasterException {
        return userService.unbanUser(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody
    List<UserBean> getAllUsers(Principal userPrincipal) throws MasterException {
        return userService.getAllUsers(userPrincipal.getName());
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody
    UserBean getUserById(@PathVariable Long id, Principal userPrincipal) throws MasterException {
        return userService.getUserById(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/user/remove/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    UserBean deleteUser(@PathVariable("id") Long id, Principal userPrincipal) throws MasterException {
        return userService.removeUser(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody
    UserBean updateUser(@RequestBody @Valid UserBean user,
            @PathVariable Long id, Principal userPrincipal) throws MasterException {
        return userService.updateUser(user, id, userPrincipal.getName());
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody
    UserBean insertUser(@RequestBody @Valid UserBean user, Principal userPrincipal) throws MasterException {
        return userService.insertUser(user, userPrincipal.getName());
    }

    @RequestMapping(value = "/user/{id}/change.role/{role}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody
    RoleDisplayer changeRole(@PathVariable Long id, @PathVariable String role) throws MasterException {
        return userService.changeRole(id, role);

    }
}
