/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.thread;

import com.example.user.UserBean;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kaloyan
 */
public interface ThreadRepository extends JpaRepository<ThreadBean, Long> {

    List<ThreadBean> findByUserPost(UserBean user);
}
