package com.epam.chat.datalayer.db;

import java.util.ResourceBundle;

public class SQLQueryHolder {
    private final static SQLQueryHolder instance = new SQLQueryHolder();
    private final ResourceBundle bundle =
            ResourceBundle.getBundle("queries");

    public static SQLQueryHolder getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
