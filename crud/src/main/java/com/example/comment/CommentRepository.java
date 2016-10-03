package com.example.comment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.example.thread.ThreadBean;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kaloyan
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCommentPost(ThreadBean post);
}
