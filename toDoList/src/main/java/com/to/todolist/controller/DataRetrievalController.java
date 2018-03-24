package com.to.todolist.controller;

import com.to.todolist.exceptions.AuthenticationException;
import com.to.todolist.service.SessionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class DataRetrievalController {

    @Autowired
    SessionManagerService sessionManagerService;

    @RequestMapping(value = "/firstmessage", method = RequestMethod.GET)
    String getGreetingMessage(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        sessionManagerService.getLoggedInUser(request, response);
        return "hello, how are you?";
    }

}
