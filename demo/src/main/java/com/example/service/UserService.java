/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.UserDao.UserRepository;
import com.example.exceptions.IncorrectInputException;
import com.example.exceptions.SuperSpecialSuperAwesomeMasterException;
import com.example.exceptions.UserNotFoundException;
import com.example.repository.User;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User insertUser(User user) throws SuperSpecialSuperAwesomeMasterException{
        try{
                return userRepository.save(user);               
            }catch (ConstraintViolationException e){
                
                Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
                
                StringBuilder message = new StringBuilder();
                message.append("There has been incorrect input for the following fields: ");
                
                for (ConstraintViolation violation : violations) {
                    message.append(violation.getPropertyPath().toString()).append("; ");
                }
                
                throw new IncorrectInputException(message.toString(), "180");
            }
    }

    public List<User> getAllUsersByLastName() {
        return userRepository.findAllByOrderByLastNameAsc();
    }

    public List<User> getAllUsersByDateOfBirth() {
        return userRepository.findAllByOrderByBirthDateAsc();
    }

    public User getUserById(Long id) throws SuperSpecialSuperAwesomeMasterException{
        User user = userRepository.findOne(id);
        if(user == null){
            throw new UserNotFoundException("there is no user with id: " + id, "113");
        }
        return user;
    }

    public User removeUser(Long id) throws SuperSpecialSuperAwesomeMasterException {
        User user = userRepository.findOne(id);
        if (user != null) {
            userRepository.delete(id);
        } else {
            throw new UserNotFoundException("Cannot remove user with id: " + id, "112");
        }
        return user;

    }

    public User updateUser(User user, Long id) throws SuperSpecialSuperAwesomeMasterException{
        User u = userRepository.findOne(id);
        if(u != null){
            u.setBirthDate(user.getBirthDate());
            u.setEmail(user.getEmail());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setPhone(user.getPhone());
            try{
                return userRepository.save(u);               
            }catch (ConstraintViolationException e){
                
                Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
                
                StringBuilder message = new StringBuilder();
                message.append("There has been incorrect input for the following fields: ");
                
                for (ConstraintViolation violation : violations) {
                    message.append(violation.getPropertyPath().toString()).append("; ");
                }
                
                throw new IncorrectInputException(message.toString(), "180");
            }
        }else {
            throw new UserNotFoundException("Cannot update user with id: " + id, "111");
        }
    }
}
