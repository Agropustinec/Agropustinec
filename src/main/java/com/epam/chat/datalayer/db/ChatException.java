package com.epam.chat.datalayer.db;

import java.sql.SQLException;

public class ChatException extends RuntimeException {
    public ChatException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
