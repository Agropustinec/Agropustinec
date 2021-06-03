package com.epam.chat.datalayer.db;

import com.epam.chat.datalayer.UserDAO;
import com.epam.chat.datalayer.connectionpool.ConnectionPool;
import com.epam.chat.datalayer.connectionpool.ConnectionPoolException;
import com.epam.chat.datalayer.dto.Role;
import com.epam.chat.datalayer.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MySqlUserDAO implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(MySqlUserDAO.class);

    public static final String KEY_USERNAME = "username";
    private static final String CONNECTION_TO_DATABASE_ERROR = "Connection to database is error";
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;
    private static final String KEY_ADD_NEW_MESSAGE = "addNewMessage";
    private static final String KEY_IS_LOGGED = "isLogged";
    private static final String GOOD_BYE_CHAT = "GoodBye Chat";
    private static final int KEY_STATUS_ID = 1;
    private static final int STATUS_KICK = 3;
    private static final int KEY_LOGOUT_STATUS = 4;
    private static final String KEY_UN_KICK = "unKick";
    private static final String KEY_GET_ROLE = "getRole";
    private static final String KEY_ROLE_NAME = "role_name";
    private static final String KEY_IS_KICKED = "isKicked";
    private static final String HELLO_CHAT = "Hello Chat";
    private static final String KEY_CREATE_USER = "createUser";
    private static final String KEY_GET_ALL_LOGGED = "getAllLogged";
    private static final String KEY_GET_ALL_KICKED = "getAllKicked";
    private final ConnectionPool connectionPool;
    private SQLQueryHolder SQLQueryHolder;

    public MySqlUserDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.SQLQueryHolder = new SQLQueryHolder();
    }

    public static void main(String[] args) {
        MySqlUserDAO mySqlUserDAO = new MySqlUserDAO(ConnectionPool.getInstance());
        Role role = mySqlUserDAO.getRole("Denis");
    }

    /**
     * @param userToLogin user we want to login
     */
    @Override
    public void login(User userToLogin) {
        if (isLoggedIn(userToLogin)) {
            LOGGER.debug("User has already login: " + userToLogin);
            return;
        }
        if (isKicked(userToLogin)) {
            LOGGER.debug("User was kicked earlier: " + userToLogin);
            return;
        }

        String nick = userToLogin.getName();
        int roleID = userToLogin.getRole().getId();
        try (Connection connection = connectionPool.takeConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(SQLQueryHolder.getValue(KEY_ADD_NEW_MESSAGE))) {
                preparedStatement.setString(PARAMETER_INDEX_ONE, nick);
                preparedStatement.setString(PARAMETER_INDEX_TWO, HELLO_CHAT);
                preparedStatement.setInt(PARAMETER_INDEX_THREE, KEY_STATUS_ID);
                createUser(connection, nick, roleID);
                preparedStatement.execute();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw e;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
    }

    /**
     * @param user user to check
     * @return
     */
    @Override
    public boolean isLoggedIn(User user) {
        boolean result = false;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_IS_LOGGED))) {
            preparedStatement.setString(PARAMETER_INDEX_ONE, user.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getBoolean(KEY_IS_LOGGED);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
        return result;
    }

    /**
     * @param userToLogout user we want to logout
     */
    @Override
    public void logout(User userToLogout) {
        addNewMessage(userToLogout.getName(), GOOD_BYE_CHAT, KEY_LOGOUT_STATUS);
    }

    @Override
    public void unkick(User user) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_UN_KICK))) {
            preparedStatement.setString(PARAMETER_INDEX_ONE, user.getName());
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
    }

    /**
     * @param admin        - user responsible for the kick action (with the role admin)
     * @param kickableUser - user that should be kicked
     */
    @Override
    public void kick(User admin, User kickableUser) {
        if (!admin.getRole().isGetRightToKick()) {
            return;
        }
        try (Connection connection = connectionPool.takeConnection()) {
            try (
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(SQLQueryHolder.getValue(KEY_ADD_NEW_MESSAGE))) {
                connection.setAutoCommit(false);
                preparedStatement.setString(PARAMETER_INDEX_ONE, admin.getName());
                preparedStatement.setString(PARAMETER_INDEX_TWO, kickableUser.getName());
                preparedStatement.setInt(PARAMETER_INDEX_THREE, STATUS_KICK);
                preparedStatement.execute();
                PreparedStatement preparedStatementLogout =
                        connection.prepareStatement(SQLQueryHolder.getValue(KEY_ADD_NEW_MESSAGE));
                preparedStatementLogout.setString(PARAMETER_INDEX_ONE, kickableUser.getName());
                preparedStatementLogout.setString(PARAMETER_INDEX_TWO, GOOD_BYE_CHAT);
                preparedStatementLogout.setInt(PARAMETER_INDEX_THREE, KEY_LOGOUT_STATUS);
                preparedStatementLogout.execute();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException | ConnectionPoolException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw e;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }

    }

    /**
     * @param user user to check
     * @return
     */
    @Override
    public boolean isKicked(User user) {
        boolean result = false;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_IS_KICKED))) {
            preparedStatement.setString(PARAMETER_INDEX_ONE, user.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getBoolean(KEY_IS_KICKED);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
        return result;
    }


    /**
     * @return
     */
    @Override
    public List<User> getAllLogged() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_GET_ALL_LOGGED))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = retrieveUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
        return userList;
    }


    /**
     * @return
     */
    @Override
    public List<User> getAllKicked() {
        List<User> userKickedList = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_GET_ALL_KICKED))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = retrieveUser(resultSet);
                userKickedList.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
        return userKickedList;
    }

    private User retrieveUser(ResultSet resultSet) throws SQLException {
        Role role = Role.valueOf(resultSet.getString(KEY_ROLE_NAME).toUpperCase());
        String username = resultSet.getString(KEY_USERNAME);
        return new User(username, role);
    }

    /**
     * @param nick nick of user to find the role
     * @return
     */
    @Override
    public Role getRole(String nick) {
        Role role = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_GET_ROLE))) {
            preparedStatement.setString(PARAMETER_INDEX_ONE, nick);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = Role.valueOf(resultSet.getString(KEY_ROLE_NAME).toUpperCase(Locale.ROOT));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
        return role;
    }

    private void addNewMessage(String username, String body, int status) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_ADD_NEW_MESSAGE))) {
            preparedStatement.setString(PARAMETER_INDEX_ONE, username);
            preparedStatement.setString(PARAMETER_INDEX_TWO, body);
            preparedStatement.setInt(PARAMETER_INDEX_THREE, status);
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
    }

    private void createUser(Connection connection, String nick, int roleID) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(SQLQueryHolder.getValue(KEY_CREATE_USER));
        preparedStatement.setString(PARAMETER_INDEX_ONE, nick);
        preparedStatement.setInt(PARAMETER_INDEX_TWO, roleID);
        preparedStatement.execute();
    }
}
