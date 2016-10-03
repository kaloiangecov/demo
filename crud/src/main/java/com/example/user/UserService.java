/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.user;

import com.example.custom.exceptions.CannotBanAdminException;
import com.example.custom.exceptions.MasterException;
import com.example.custom.exceptions.MissingResourceOrBadPermission;
import com.example.custom.exceptions.UserIsBannedException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaloyan
 */
@Service
public class UserService{

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RoleRepository roleRepo;

    public UserBean insertUser(UserBean user, String username) throws MasterException {
        checkIfBanned(username);
        
        userRepo.save(user);

        return user;
    }

    public UserBean updateUser(UserBean user, Long id, String username) throws MasterException {
        checkIfBanned(username);
        
        UserBean u = userRepo.findOne(id);

        u.setPassword(user.getPassword());
        u.setUsername(user.getUsername());
        u.setIsBanned(user.isIsBanned());

        return userRepo.save(u);
    }

    public UserBean getUserById(Long id, String username) throws MasterException {
        checkIfBanned(username);
        return userRepo.findOne(id);
    }
    
    public List<UserBean> getAllUsers(String username) throws MasterException {
        checkIfBanned(username);
        return userRepo.findAll();
    }

    public UserBean removeUser(Long id, String username) throws MasterException {
        checkIfBanned(username);
        
        UserBean user = userRepo.findOne(id);

        if (user != null) {
            userRepo.delete(id);
        } else {
            throw new MissingResourceOrBadPermission("", "124");
        }

        return user;
    }

    public UserBean banUser(Long id, String username) throws MasterException{
        
        UserBean user = userRepo.findOne(id);

        if (!user.getRole().getRolename().equals("ROLE_ADMIN")) {
            user.setIsBanned(true);
            userRepo.save(user);
            return user;
        } else {
            throw new CannotBanAdminException("", "21414");
        }
    }

    public UserBean unbanUser(Long id, String username) {
        
        UserBean user = userRepo.findOne(id);
        user.setIsBanned(false);
        
        userRepo.save(user);
        return user;

    }
    
    public RoleDisplayer changeRole(Long id, String role) throws MasterException{
        
        RoleDisplayer roleDisplayer = new RoleDisplayer();
        
        UserBean user = userRepo.findOne(id);
        
        if((role.equals("ROLE_USER") || role.equals("ROLE_SKRUB")) && user != null && !user.getRole().getRolename().equals("ROLE_ADMIN")){
            user.setRole(roleRepo.findByRolename(role));
            userRepo.save(user);
            
            roleDisplayer.setUsername(user.getUsername());
            roleDisplayer.setMessage("Role successfuly changed");
            roleDisplayer.setUserRole(role);

            return roleDisplayer;
        }else {
            throw new MissingResourceOrBadPermission("", "124");
        }
    }
    
    private void checkIfBanned(String username) throws MasterException {
        UserBean user = userRepo.findByUsername(username);

        if (user.isIsBanned()) {
            throw new UserIsBannedException("", "123");
        }
    }


}
