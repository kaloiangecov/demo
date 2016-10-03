/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.thread;

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
public class ThreadController {
    
    @Autowired
    private ThreadService postService;
    
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody List<ThreadBean> getAllPosts(Principal userPrincipal)
                                                            throws MasterException {
        return postService.getAllPosts(userPrincipal.getName());
    }
    
    @RequestMapping(value = "/post/user/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody List<ThreadBean> getAllPostsByUserId(@PathVariable Long id,
                                                              Principal userPrincipal) throws MasterException {
        return postService.getAllPostsByUserId(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody ThreadBean getPostById(@PathVariable Long id,
                                                Principal userPrincipal) throws MasterException {
        return postService.getPostById(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/post/remove/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public @ResponseBody ThreadBean deletePost(Principal userPrincipal,
                                               @PathVariable("id") Long id) throws MasterException {
        return postService.removePost(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody ThreadBean updatePost(@RequestBody @Valid ThreadBean post,
                                               @PathVariable Long id, Principal userPrincipal) throws MasterException {
        return postService.updatePost(post, id, userPrincipal.getName());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody ThreadBean insertPost(@RequestBody @Valid ThreadBean post,
                                               Principal userPrincipal) throws MasterException {
        return postService.insertPost(post, userPrincipal.getName());
    }
}
