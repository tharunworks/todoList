package com.to.todolist.service;

import com.to.todolist.models.User;
import com.to.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(String userEmail, String password) {
        User  user = userRepository.findByUserEmailAndPassWord(userEmail, password);
        return user == null ? false : true;
    }
}
