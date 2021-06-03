package com.epam.chat.datalayer.xml;

import com.epam.chat.datalayer.UserDAO;
import com.epam.chat.datalayer.dto.Message;
import com.epam.chat.datalayer.dto.Role;
import com.epam.chat.datalayer.dto.Status;
import com.epam.chat.datalayer.dto.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XMLUserDAO implements UserDAO {
    public static final String GREETING = "hello chat";
    public static final String PARTING = "goodbye chat";
    //place XMLDomParser or XMLUserParser here
    private ParseXml parseXml;
    private Map<String, Boolean> usersMapStatus;
    private Map<String, Boolean> usersMapIsKicked;

    public XMLUserDAO(ParseXml parseXml) {
        this.parseXml = parseXml;
        this.usersMapStatus = createMapUsers();
        this.usersMapIsKicked = createMapKickedUsers();
    }

    /**
     * Login user using xml parser
     *
     * @param userToLogin user we want to login
     */
    @Override
    public void login(User userToLogin) {
        if (usersMapStatus.containsKey(userToLogin.getName())) {
            return;
        }
        parseXml.addNewMessage(parseXml.readXml(), userToLogin.getName(), new Timestamp(new Date().getTime()).toString(), GREETING, Status.LOGIN);
        parseXml.addNewUser(parseXml.readXml(), userToLogin.getName(), userToLogin.getRole());
    }

    /**
     * Check user logged in using parser
     *
     * @param user user to check
     * @return boolean result
     */
    @Override
    public boolean isLoggedIn(User user) {
        return usersMapStatus.get(user.getName());
    }

    private boolean retrieveBooleanByStatus(Status status, List<Message> messageList, int i) {
        return messageList.get(i).getStatus().equals(status.name());
    }

    /**
     * Logout user using xml file
     *
     * @param userToLogout user we want to logout
     */
    @Override
    public void logout(User userToLogout) {
        parseXml.addNewMessage(parseXml.readXml(), userToLogout.getName(), new Timestamp(new Date().getTime()).toString(), PARTING, Status.LOGOUT);
    }

    /**
     * Unckick user using xml parser
     *
     * @param user user we want to logout
     */
    @Override
    public void unkick(User user) {
        parseXml.removeMessageAboutKick(parseXml.readXml(), user.getName());
    }


    /**
     * @param admin        - user responsible for the kick action (with the role admin)
     * @param kickableUser - user that should be kicked
     */
    @Override
    public void kick(User admin, User kickableUser) {
        if (!admin.getRole().equals(Role.ADMIN)) {
            return;
        }
        parseXml.addNewMessage(parseXml.readXml(), admin.getName(), new Timestamp(new Date().getTime()).toString(), kickableUser.getName(), Status.KICK);
    }

    /**
     * Check user is kicked using xml parser
     *
     * @param user user to check
     * @return boolean result
     */
    @Override
    public boolean isKicked(User user) {
        return usersMapIsKicked.get(user.getName());
    }

    private Map<String, Boolean> createMapUsers() {
        Map<String, Boolean> userMap = new HashMap<>();
        List<Message> messageList = parseXml.createMessageList();
        for (int i = messageList.size() - 1; i <= 0; i++) {
            loginCheck(userMap, messageList, i);
            logoutCheck(userMap, messageList, i);
            kickedCheck(userMap, messageList, i, false);
        }

        return userMap;
    }

    private Map<String, Boolean> createMapKickedUsers() {
        Map<String, Boolean> userKickedMap = new HashMap<>();
        List<Message> messageList = parseXml.createMessageList();
        for (int i = messageList.size() - 1; i <= 0; i++) {
            kickedCheck(userKickedMap, messageList, i, true);
        }
        return userKickedMap;
    }

    private void kickedCheck(Map<String, Boolean> userMap, List<Message> messageList, int i, boolean mapValue) {
        if (messageList.get(i).getStatus().equals(Status.KICK)) {
            userMap.put(messageList.get(i).getMessageContent(), false);
        }
    }

    private void logoutCheck(Map<String, Boolean> userMap, List<Message> messageList, int i) {
        if (messageList.get(i).getStatus().equals(Status.LOGOUT)) {
            if (userMap.containsKey(messageList.get(i).getUserFrom())) {
                return;
            }
            userMap.put(messageList.get(i).getUserFrom(), false);
        }
    }

    private void loginCheck(Map<String, Boolean> userMap, List<Message> messageList, int i) {
        if (messageList.get(i).getStatus().equals(Status.LOGIN)) {
            if (userMap.containsKey(messageList.get(i).getUserFrom())) {
                return;
            }
            userMap.put(messageList.get(i).getUserFrom(), true);
        }
    }

    /**
     * Get all logged users using xml parser
     *
     * @return all logged users from xml file
     */
    @Override
    public List<User> getAllLogged() {
        Set<User> loggedUsers = new HashSet<>();
        List<User> users = parseXml.createListUser();
        for (User user : users) {
            if (isLoggedIn(user)) {
                loggedUsers.add(user);
            }
        }
        return new ArrayList<>(loggedUsers);
    }


    /**
     * Get all kicked users
     *
     * @return
     */
    @Override
    public List<User> getAllKicked() {
        Set<User> kickedUsers = new HashSet<>();
        List<User> users = parseXml.createListUser();
        for (User user : users) {
            if (isKicked(user)) {
                kickedUsers.add(user);
            }
        }
        return new ArrayList<>(kickedUsers);
    }

    /**
     * Get role from xml file using parser by user nick
     *
     * @param nick nick of user to find the role
     * @return user role
     */
    @Override
    public Role getRole(String nick) {
        List<User> users = parseXml.createListUser();
        for (User user : users) {
            if (user.getName().equals(nick)) {
                return user.getRole();
            }
        }
        return null;
    }
}