/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.thread;

import com.example.custom.exceptions.MasterException;
import com.example.custom.exceptions.MissingResourceOrBadPermission;
import com.example.custom.exceptions.UserIsBannedException;
import com.example.user.UserBean;
import com.example.user.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaloyan
 */
@Service
public class ThreadService {

    @Autowired
    private ThreadRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    public List<ThreadBean> getAllPostsByUserId(Long id, String username) throws MasterException {
        checkIfBanned(username);
        return postRepo.findByUserPost(userRepo.findOne(id));
    }

    public List<ThreadBean> getAllPosts(String username) throws MasterException {
        checkIfBanned(username);
        return postRepo.findAll();
    }

    public ThreadBean getPostById(Long id, String username) throws MasterException {
        checkIfBanned(username);
        return postRepo.findOne(id);
    }

    public ThreadBean removePost(Long id, String username) throws MasterException {
        checkIfBanned(username);

        ThreadBean post = postRepo.findOne(id);

        if (post != null && post.getAuthor().equals(username)) {

            postRepo.delete(post);
        } else {
            throw new MissingResourceOrBadPermission("", "124");
        }

        return post;
    }

    public ThreadBean updatePost(ThreadBean post, Long id, String username) throws MasterException {
        checkIfBanned(username);

        ThreadBean pst = postRepo.findOne(id);

        if (pst.getAuthor().equals(username)) {
            pst.setTitle(post.getTitle());
            pst.setText(post.getText());

            return postRepo.save(pst);
        } else {
            throw new UserIsBannedException("", "123");
        }
    }

    public ThreadBean insertPost(ThreadBean post, String username) throws MasterException {
        checkIfBanned(username);

        post.setAuthor(username);

        post.setUserPost(userRepo.findByUsername(username));

        postRepo.save(post);

        return post;
    }

    private void checkIfBanned(String username) throws MasterException {
        UserBean user = userRepo.findByUsername(username);

        if (user.isIsBanned()) {
            throw new UserIsBannedException("", "123");
        }
    }

}
