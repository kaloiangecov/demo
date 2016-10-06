/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.thread;

import com.example.user.UserBean;
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
public class ThreadBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private Long id;

    @NotNull
    @Size(min = 5, max = 30)
    private String title;

    private String author;

    @NotNull
    @Size(min = 2, max = 200)
    private String text;

    private Date postedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserBean userPost;

    public ThreadBean() {
        this.postedOn = new Date();
    }

    public ThreadBean(String author, String title, String text, UserBean userPost) {
        this.author = author;
        this.title = title;
        this.text = text;
        this.postedOn = new Date();
        this.userPost = userPost;
    }

}
