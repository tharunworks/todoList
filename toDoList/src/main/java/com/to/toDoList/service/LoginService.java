package com.to.todolist.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean validateUser(String userid, String password) {
        return userid.equalsIgnoreCase("in28minutes")
                && password.equalsIgnoreCase("dummy");
    }
}
