package com.epam.chat.datalayer.dto;

public enum Role {
    ADMIN("Admin role description", true, 1),
    USER("User role description", false, 2);
    private String description;
    private boolean getRightToKick;
    private int id;

    Role(String description, boolean getRightToKick, int id) {
        this.description = description;
        this.getRightToKick = getRightToKick;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isGetRightToKick() {
        return getRightToKick;
    }

    public int getId() {
        return id;
    }
}
