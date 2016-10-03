package com.example.comment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.example.user.UserBean;
import com.example.thread.ThreadBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author kaloyan
 */
@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long id;
    private String author;
    @NotNull
    @Size(min = 2, max = 200)
    private String text;

    private Date commentedOn;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private ThreadBean commentPost;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private UserBean userPost;

    public Comment() {
        this.commentedOn = new Date();
    }

    public Comment(String author, String text, ThreadBean commentPost, UserBean userPost) {
        this.commentedOn = new Date();
        this.author = author;
        this.text = text;
        this.commentPost = commentPost;
        this.userPost = userPost;
    }

}
