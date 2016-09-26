/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository;

import com.example.model.UserBean;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kaloyan
 */
@Repository
public interface UserRepository extends JpaRepository<UserBean, Long> {

    UserBean findByUsername(String username);
    
    List<UserBean> findAllByOrderByLastNameAsc();

    List<UserBean> findAllByOrderByBirthDateAsc();

}
