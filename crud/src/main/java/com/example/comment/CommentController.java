package com.example.comment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @RequestMapping(value = "/post/comments/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody List<Comment> getAllCommentsForPost(@PathVariable Long id,
                                                             Principal userPrincipal) throws MasterException {
        return commentService.findAllCommentsForPostId(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/post/comment/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody Comment getCommentById(@PathVariable Long id,
                                                Principal userPrincipal) throws MasterException {
        return commentService.findCommentById(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/post/comment/remove/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public @ResponseBody Comment deleteComment(@PathVariable("id") Long id,
                                               Principal userPrincipal) throws MasterException {
        return commentService.removeComment(id, userPrincipal.getName());
    }

    @RequestMapping(value = "/post/comment/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody Comment updateComment(@RequestBody @Valid Comment comment,
                                               @PathVariable Long id, Principal userPrincipal) throws MasterException {
        return commentService.updateComment(comment, id, userPrincipal.getName());
    }

    @RequestMapping(value = "/post/{id}/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    public @ResponseBody Comment insertUser(@RequestBody @Valid Comment comment, 
                                            @PathVariable Long id, Principal userPrincipal) throws MasterException {
        return commentService.insertComment(comment, id, userPrincipal.getName());
    }
}
