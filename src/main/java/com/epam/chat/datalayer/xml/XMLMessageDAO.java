package com.epam.chat.datalayer.xml;

import com.epam.chat.datalayer.MessageDAO;
import com.epam.chat.datalayer.dto.Message;

import java.util.ArrayList;
import java.util.List;

public class XMLMessageDAO implements MessageDAO {

    private ParseXml parseXml;

    public XMLMessageDAO(ParseXml parseXml) {
        this.parseXml = parseXml;
    }

    @Override
    /*
     * Get last messages from xml file using parser
     */
    public List<Message> getLast(int count) {
        List<Message> messageList = parseXml.createMessageList();
        if (count > messageList.size()) {
            count = messageList.size();
        }
        List<Message> result = new ArrayList<>();
        for (int i = messageList.size() - count; i < messageList.size(); i++) {
            result.add(messageList.get(i));
        }
        return result;
    }

    /**
     * Send message using xml file and parse
     *
     * @param message - message to send
     */
    @Override
    public void sendMessage(Message message) {
        parseXml.addNewMessage(parseXml.readXml(), message.getUserFrom(), message.getTimestamp().toString(), message.getMessageContent(),
                message.getStatus());
    }
}
