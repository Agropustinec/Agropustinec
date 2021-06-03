package com.epam.chat.datalayer.dto;


import java.sql.Timestamp;
import java.util.Objects;

/**
 * Represents chat message
 */
public class Message {
    private String userFrom;
    private Timestamp timestamp;
    private String messageContent;
    private Status status;


    public Message(String userFrom, Timestamp timestamp, String messageContent, Status status) {
        this.userFrom = userFrom;
        this.timestamp = timestamp;
        this.messageContent = messageContent;
        this.status = status;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessageContent() {
        return messageContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(userFrom, message.userFrom) && Objects.equals(timestamp, message.timestamp) && Objects.equals(messageContent, message.messageContent) && status == message.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userFrom, timestamp, messageContent, status);
    }

    @Override
    public String toString() {
        return "Message{" +
                "userFrom='" + userFrom + '\'' +
                ", timestamp=" + timestamp +
                ", messageContent='" + messageContent + '\'' +
                ", status=" + status +
                '}';
    }
}
