package com.assignment4.assignment4_loginDemo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment4.assignment4_loginDemo.dao.UserDao;
import com.assignment4.assignment4_loginDemo.model.User;

@Service
public class UserService {
@Autowired
private UserDao userDao;

public int saveUser(User user) {
	 
	return userDao.saveUser(user);
}

public boolean authenticate(String email, String password) {
    Optional<User> userOptional = userDao.findByEmail(email);
    if (userOptional.isPresent()) {
        User user = userOptional.get();
        return user.getPassword().equals(password); 
    }
    return false;
}
public Optional<User> getUserByEmail(String email) {
    return userDao.findByEmail(email);
}


}
