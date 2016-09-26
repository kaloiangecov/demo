/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.repository.UserRepository;
import com.example.exceptions.IncorrectInputException;
import com.example.exceptions.SuperSpecialSuperAwesomeMasterException;
import com.example.exceptions.UserNotFoundException;
import com.example.model.RolePermission;
import com.example.model.UserBean;
import com.example.model.UserRole;
import com.example.repository.UserRoleRepository;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaloyan
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<UserBean> getAllUsers() {
        return userRepository.findAll();
    }

    public UserBean insertUser(UserBean user) throws SuperSpecialSuperAwesomeMasterException {
        try {
            user.setUserRole(userRoleRepository.findOne(1L));
            userRepository.save(user);
            UserRole userRole = userRoleRepository.findOne(1L);
            userRole.getUsers().add(user);
            userRoleRepository.save(userRole);

            return user;
        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

            StringBuilder message = new StringBuilder();
            message.append("There has been incorrect input for the following fields: ");

            for (ConstraintViolation violation : violations) {
                message.append(violation.getPropertyPath().toString()).append("; ");
            }

            throw new IncorrectInputException(message.toString(), "180");
        }
    }

    public List<UserBean> getAllUsersByLastName() {
        return userRepository.findAllByOrderByLastNameAsc();
    }

    public List<UserBean> getAllUsersByDateOfBirth() {
        return userRepository.findAllByOrderByBirthDateAsc();
    }

    public UserBean getUserById(Long id) throws SuperSpecialSuperAwesomeMasterException {
        UserBean user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("there is no user with id: " + id, "113");
        }

        System.out.println(user.getUserRole().getRoleName());
        for (RolePermission permission : user.getUserRole().getRolePermissions()) {
            System.out.println(permission.getName());
        }
        return user;
    }

    public UserBean removeUser(Long id) throws SuperSpecialSuperAwesomeMasterException {
        UserBean user = userRepository.findOne(id);
        if (user != null) {
            userRepository.delete(id);
        } else {
            throw new UserNotFoundException("Cannot remove user with id: " + id, "112");
        }
        return user;

    }

    public UserBean updateUser(UserBean user, Long id) throws SuperSpecialSuperAwesomeMasterException {
        UserBean u = userRepository.findOne(id);
        if (u != null) {
            
            u.setBirthDate(user.getBirthDate());
            u.setEmail(user.getEmail());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setPhone(user.getPhone());
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            u.setUserRole(user.getUserRole());
            try {
                return userRepository.save(u);
            } catch (ConstraintViolationException e) {

                Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

                StringBuilder message = new StringBuilder();
                message.append("There has been incorrect input for the following fields: ");

                for (ConstraintViolation violation : violations) {
                    message.append(violation.getPropertyPath().toString()).append("; ");
                }

                throw new IncorrectInputException(message.toString(), "180");
            }
        } else {
            throw new UserNotFoundException("Cannot update user with id: " + id, "111");
        }
    }

}
