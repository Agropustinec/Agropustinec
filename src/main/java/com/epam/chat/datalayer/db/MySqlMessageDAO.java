package com.epam.chat.datalayer.db;

import com.epam.chat.datalayer.MessageDAO;
import com.epam.chat.datalayer.connectionpool.ConnectionPool;
import com.epam.chat.datalayer.connectionpool.ConnectionPoolException;
import com.epam.chat.datalayer.dto.Message;
import com.epam.chat.datalayer.dto.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MySqlMessageDAO implements MessageDAO {
    private static final int PARAMETER_INDEX_ONE = 1;
    private static final int PARAMETER_INDEX_TWO = 2;
    private static final int PARAMETER_INDEX_THREE = 3;
    private static final String CONNECTION_TO_DATABASE_ERROR = "Connection to database is error";
    private static final String KEY_ADD_NEW_MESSAGE = "addNewMessage";
    private static final String KEY_GET_LAST_MESSAGES = "getLastMessages";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_BODY = "body";
    private static final String KEY_MESSAGE_STATUS_ID = "message_status_id";
    private static final String KEY_GET_STATUS_BY_ID = "getStatusNameById";
    public static final String KEY_STATUS_NAME = "status_name";
    private final ConnectionPool connectionPool;
    private SQLQueryHolder SQLQueryHolder;

    public MySqlMessageDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.SQLQueryHolder = SQLQueryHolder.getInstance();
    }

    @Override
    public void sendMessage(Message message) {
        String messageText = message.getMessageContent();
        int statusID = message.getStatus().getId(message.getStatus());
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_ADD_NEW_MESSAGE))) {
            preparedStatement.setString(PARAMETER_INDEX_ONE, message.getUserFrom());
            preparedStatement.setString(PARAMETER_INDEX_TWO, messageText);
            preparedStatement.setInt(PARAMETER_INDEX_THREE, statusID);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
    }

    @Override
    public List<Message> getLast(int count) {
        List<Message> messageList = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_GET_LAST_MESSAGES))) {
            preparedStatement.setInt(PARAMETER_INDEX_ONE, count);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String userFrom = resultSet.getString(KEY_USERNAME);
                Timestamp timestamp = Timestamp.valueOf(resultSet.getString(KEY_CREATED_AT));
                String messageContent = resultSet.getString(KEY_BODY);
                Status status = getStatusById(resultSet.getInt(KEY_MESSAGE_STATUS_ID));
                Message message = new Message(
                        userFrom,
                        timestamp, messageContent, status
                );
                messageList.add(message);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
        return messageList;
    }


    private Status getStatusById(int id) {
        Status result = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQueryHolder.getValue(KEY_GET_STATUS_BY_ID))) {
            preparedStatement.setInt(PARAMETER_INDEX_ONE, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = Status.valueOf(resultSet.getString(KEY_STATUS_NAME).toUpperCase());
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new ChatException(CONNECTION_TO_DATABASE_ERROR, e);
        }
        return result;
    }
}

