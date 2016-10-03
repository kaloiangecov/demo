package com.example.comment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.example.custom.exceptions.MasterException;
import com.example.custom.exceptions.MissingResourceOrBadPermission;
import com.example.custom.exceptions.UserIsBannedException;
import com.example.user.UserBean;
import com.example.user.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.thread.ThreadRepository;

/**
 *
 * @author kaloyan
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private ThreadRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    public List<Comment> findAllCommentsForPostId(Long id, String username) throws MasterException {
        checkIfBanned(username);
        return commentRepo.findByCommentPost(postRepo.findOne(id));
    }

    public Comment findCommentById(Long id, String username) throws MasterException {
        checkIfBanned(username);
        return commentRepo.findOne(id);
    }

    public Comment insertComment(Comment comment, Long id, String username) throws MasterException {
        UserBean user = userRepo.findByUsername(username);

        checkIfBanned(user);

        if (postRepo.findOne(id) != null) {
            comment.setAuthor(username);
            comment.setUserPost(user);
            comment.setCommentPost(postRepo.findOne(id));

            commentRepo.save(comment);

            return comment;
        } else {
            throw new MissingResourceOrBadPermission("", "124");
        }
    }

    public Comment removeComment(Long id, String username) throws MasterException {
        UserBean user = userRepo.findByUsername(username);

        checkIfBanned(user);

        Comment comment = commentRepo.findOne(id);

        if (comment != null && comment.getAuthor().equals(user.getUsername())) {
            commentRepo.delete(comment);
        } else {
            throw new MissingResourceOrBadPermission("", "124");
        }

        return comment;
    }

    public Comment updateComment(Comment comment, Long id, String username) throws MasterException {
        UserBean user = userRepo.findByUsername(username);

        checkIfBanned(user);

        Comment cmmnt = commentRepo.findOne(id);

        if (cmmnt.getAuthor().equals(user.getUsername())) {
            cmmnt.setText(comment.getText());
            return commentRepo.save(cmmnt);
        } else {
            throw new MissingResourceOrBadPermission("", "124");
        }

    }

    private void checkIfBanned(String username) throws MasterException {
        UserBean user = userRepo.findByUsername(username);

        if (user.isIsBanned()) {
            throw new UserIsBannedException("", "123");
        }
    }

    private void checkIfBanned(UserBean user) throws MasterException {
        if (user.isIsBanned()) {
            throw new UserIsBannedException("", "123");
        }
    }

}
