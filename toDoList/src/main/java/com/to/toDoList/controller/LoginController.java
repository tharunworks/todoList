package com.to.toDoList.controller;

import com.to.toDoList.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
        boolean isValidUser = loginService.validateUser(name, password);
        if (!isValidUser) {
            //model.put(#placeholder, #value) in login.jsp
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
        //model.put(#placeholder, #value) in welcome.jsp
        model.put("name", name);
        model.put("password", password);
        return "welcome";
    }
}
