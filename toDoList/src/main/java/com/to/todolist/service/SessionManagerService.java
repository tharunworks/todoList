package com.to.todolist.service;

import com.to.todolist.Constants;
import com.to.todolist.dtos.LoggedInUser;
import com.to.todolist.dtos.LoginDto;
import com.to.todolist.exceptions.AuthenticationException;
import com.to.todolist.models.User;
import com.to.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class SessionManagerService {

    //TODO: use datastore for caching in future
    private static Map<String, LoggedInUser> loggedInUserMap = new HashMap<>();

    @Autowired
    private UserRepository userRepository;

    public LoggedInUser setSessionUser(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        LoggedInUser loggedInUser = new LoggedInUser();
        HttpSession httpSession = request.getSession();
        User user = userRepository.findByUserEmailAndPassWord(loginDto.getUserEmail(), loginDto.getPassword());

        httpSession.setAttribute(Constants.FULL_NAME_TOKEN, user.getUserName());
        httpSession.setAttribute(Constants.JSESSION_ID_TOKEN, httpSession.getId());

        loggedInUser.setFullName(user.getUserName());
        loggedInUser.setJsessionId(httpSession.getId());
        loggedInUser.setUserId(user.getUserId());

        loggedInUserMap.putIfAbsent(httpSession.getId(), loggedInUser);

        return loggedInUser;
    }

    public LoggedInUser getLoggedInUser(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute(Constants.JSESSION_ID_TOKEN) != null){
            return loggedInUserMap.get(httpSession.getAttribute(Constants.JSESSION_ID_TOKEN));
        }
        //TODO: should throw 401 error code instead of 500 error code.
        throw new AuthenticationException("Authentication Failed");
    }

    public void logoutLoggedInUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession httpSession = request.getSession();
        loggedInUserMap.remove(httpSession.getId());
        httpSession.invalidate();
    }
}
