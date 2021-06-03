package com.epam.chat.commands.impl;

import com.epam.chat.commands.Command;
import com.epam.chat.datalayer.UserDAO;
import com.epam.chat.datalayer.dto.Role;
import com.epam.chat.datalayer.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private final UserDAO userDAO;

    public LoginCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        User user = create(username);

//        userDAO.login(user);
        boolean loggedIn = false;//userDAO.isLoggedIn(user);

        RequestDispatcher dispatcher;
        if (loggedIn) {
            dispatcher = req.getRequestDispatcher("/WEB-INF/pages/chat.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("/WEB-INF/pages/login.jsp");
            req.setAttribute("errorMsg", "Failed to login");
        }

        try {
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User create(String username) {
        return new User(username, Role.USER);
    }
}