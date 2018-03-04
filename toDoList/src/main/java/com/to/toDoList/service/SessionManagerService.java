package com.to.toDoList.service;

import com.to.toDoList.dtos.LoggedInUser;
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
        httpSession.setAttribute("fullName", model.get("name"));
        httpSession.setAttribute("jsessionId", httpSession.getId());
        loggedInUser.setFullName((String) model.get("name"));
        loggedInUser.setJsessionId(httpSession.getId());
        return loggedInUser;
    }

    public LoggedInUser getLoggedInUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession httpSession = request.getSession();
        LoggedInUser loggedInUser = new LoggedInUser();
        if(httpSession.getAttribute("jsessionId") != null){
            loggedInUser.setFullName((String) httpSession.getAttribute("fullName"));
            loggedInUser.setJsessionId((String) httpSession.getAttribute("jsessionId"));
            return loggedInUser;
        }
        return null;
    }

    public void logoutLoggedInUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
    }
}
