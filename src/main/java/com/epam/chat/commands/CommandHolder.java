package com.epam.chat.commands;

import com.epam.chat.commands.impl.LoginCommand;
import com.epam.chat.datalayer.DAOFactory;
import com.epam.chat.datalayer.DBType;

public class CommandHolder {
    private final LoginCommand loginCommand;


    public CommandHolder(DBType dbType) {
        DAOFactory daoFactory = DAOFactory.getInstance(dbType);
        this.loginCommand = new LoginCommand(daoFactory.getUserDAO());
    }

    public Command defineCommand(String commandName) {
        return loginCommand;
    }
}
