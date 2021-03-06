package com.to.todolist.controller;

import com.to.todolist.dtos.LoggedInUser;
import com.to.todolist.dtos.LoginDto;
import com.to.todolist.exceptions.AuthenticationException;
import com.to.todolist.repositories.TaskRepository;
import com.to.todolist.service.LoginService;
import com.to.todolist.service.SessionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionManagerService sessionManagerService;

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){

        /*
        note:
        if return "login2"
        response: {"timestamp":"2018-03-03T11:35:20.782+0000","status":404,"error":"Not Found","message":"/WEB-INF/jsp/login2.jsp","path":"/login"}
         */
        return "login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam(value = "name") String userEmail, @RequestParam String password, HttpServletRequest request, HttpServletResponse response){
        boolean isValidUser = loginService.validateUser(userEmail, password);
        if (!isValidUser) {
            //note:model.put(#placeholder, #value) in login.jsp
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
        //note:model.put(#placeholder, #value) in welcome.jspx
        LoginDto loginDto = new LoginDto();
        loginDto.setUserEmail(userEmail);
        loginDto.setPassword(password);
        model.put("name", loginDto.getUserEmail());
        model.put("password", loginDto.getPassword());
        sessionManagerService.setSessionUser(loginDto, request, response);
        return "welcome";
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showToDoPage(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoggedInUser loggedInUser = sessionManagerService.getLoggedInUser(request, response);
        model.put("checkName", loggedInUser.getFullName());
        model.put("checkJsessionId", loggedInUser.getJsessionId());
        model.put("tasks", taskRepository.findAllByUserId(loggedInUser.getUserId()));
        return "list-todos";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        sessionManagerService.logoutLoggedInUser(request, response);
        return "redirect:login";
    }
}
