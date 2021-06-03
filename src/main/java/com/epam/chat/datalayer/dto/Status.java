package com.epam.chat.datalayer.dto;

public enum Status {
    LOGIN("User logged into chat"),
    MESSAGE("User left a message"),
    KICK("The user threw another user out of the chat"),
    LOGOUT("The user has left the chat");
    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId(Status status) {
        int result = 0;
        switch (status) {
            case LOGIN:
                result = 1;
                break;
            case MESSAGE:
                result = 2;
            case KICK:
                result = 3;
                break;
            case LOGOUT:
                result = 4;
                break;
        }
        return result;
    }
}