package com.to.todolist.controller;

import com.to.todolist.dtos.LoggedInUser;
import com.to.todolist.exceptions.AuthenticationException;
import com.to.todolist.models.Task;
import com.to.todolist.models.User;
import com.to.todolist.repositories.TaskRepository;
import com.to.todolist.repositories.UserRepository;
import com.to.todolist.service.SessionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DataRetrievalController {

    @Autowired
    private SessionManagerService sessionManagerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/firstmessage", method = RequestMethod.GET)
    String getGreetingMessage(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        sessionManagerService.getLoggedInUser(request, response);
        return "hello, how are you?";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    User getUser(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoggedInUser loggedInUser = sessionManagerService.getLoggedInUser(request, response);
        return userRepository.findByUserId(loggedInUser.getUserId());
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    List<Task> getTasks(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoggedInUser loggedInUser = sessionManagerService.getLoggedInUser(request, response);
        return taskRepository.findAllByUserId(loggedInUser.getUserId());
    }
}