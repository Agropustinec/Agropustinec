package com.epam.chat.datalayer.db;

import com.epam.chat.datalayer.DAOFactory;
import com.epam.chat.datalayer.MessageDAO;
import com.epam.chat.datalayer.UserDAO;
import com.epam.chat.datalayer.connectionpool.ConnectionPool;

/**
 *
 */
public class MySqlDAOFactory extends DAOFactory {

    @Override
    public MessageDAO getMessageDAO() {
        return new MySqlMessageDAO(ConnectionPool.getInstance());
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAO(ConnectionPool.getInstance());
    }
}