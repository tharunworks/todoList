package com.to.todolist.service;

import com.to.todolist.Constants;
import com.to.todolist.dtos.LoggedInUser;
import com.to.todolist.exceptions.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class SessionManagerService {
    public LoggedInUser setSessionUser(ModelMap model, HttpServletRequest request, HttpServletResponse response){
        LoggedInUser loggedInUser = new LoggedInUser();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(Constants.FULL_NAME_TOKEN, model.get("name"));
        httpSession.setAttribute(Constants.JSESSION_ID_TOKEN, httpSession.getId());
        loggedInUser.setFullName((String) model.get("name"));
        loggedInUser.setJsessionId(httpSession.getId());
        return loggedInUser;
    }

    public LoggedInUser getLoggedInUser(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        HttpSession httpSession = request.getSession();
        LoggedInUser loggedInUser = new LoggedInUser();
        if(httpSession.getAttribute(Constants.JSESSION_ID_TOKEN) != null){
            loggedInUser.setFullName((String) httpSession.getAttribute(Constants.FULL_NAME_TOKEN));
            loggedInUser.setJsessionId((String) httpSession.getAttribute(Constants.JSESSION_ID_TOKEN));
            return loggedInUser;
        }
        //TODO: should throw 401 error code instead of 500 error code.
        throw new AuthenticationException("Authentication Failed");
    }

    public void logoutLoggedInUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
    }
}
