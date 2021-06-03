package com.epam.chat.datalayer.xml;

import com.epam.chat.datalayer.DAOFactory;
import com.epam.chat.datalayer.MessageDAO;
import com.epam.chat.datalayer.UserDAO;

public class XMLDAOFactory extends DAOFactory {

    private ParseXml parseXml = new ParseXml();

    @Override
    public MessageDAO getMessageDAO() {
        return new XMLMessageDAO(parseXml);
    }

    @Override
    public UserDAO getUserDAO() {
        return new XMLUserDAO(parseXml);
    }
}