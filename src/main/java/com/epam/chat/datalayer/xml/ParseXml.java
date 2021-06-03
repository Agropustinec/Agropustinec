package com.epam.chat.datalayer.xml;

import com.epam.chat.datalayer.dto.Message;
import com.epam.chat.datalayer.dto.Role;
import com.epam.chat.datalayer.dto.Status;
import com.epam.chat.datalayer.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParseXml {
    private static final Logger LOGGER = LogManager.getLogger(ParseXml.class);
    private static final String KEY_FOR_USER_DIR = "user.dir";
    public static final String PATH_XML = KEY_FOR_USER_DIR + "\\src\\main\\resources\\chat.xml";
    public static final String TAG_NAME_ROLE = "role";
    public static final String TAG_NAME_NAME = "name";
    public static final String TAG_NAME_USER = "user";
    public static final String TAG_NAME_USERS = "users";
    public static final String TAG_NAME_MESSAGES = "messages";
    public static final String TAG_NAME_MESSAGE = "message";
    public static final String TAG_NAME_MESSAGE_CONTENT = "messageContent";
    public static final String TAG_NAME_STATUS = "status";
    public static final String TAG_NAME_USER_FROM = "user_from";
    public static final String TAG_NAME_TIME_STAMP = "time_stamp";

    public List<User> createListUser() {
        Document document = readXml();

        if (document == null) {
            return Collections.emptyList();
        }

        Element usersItem = (Element) document.getElementsByTagName(TAG_NAME_USERS).item(0);
        NodeList users = usersItem.getElementsByTagName(TAG_NAME_USER);

        List<User> result = new ArrayList<>();
        for (int i = 0; i < users.getLength(); i++) {
            if (!(users.item(i) instanceof Element)) {
                continue;
            }
            Element user = (Element) users.item(i);
            String name = retrieveByTagNameForUser(user, TAG_NAME_NAME);
            Role role = Role.valueOf(retrieveByTagNameForUser(user, TAG_NAME_ROLE));
            result.add(new User(name, role));
        }
        return result;
    }

    public List<Message> createMessageList() {
        Document document = readXml();

        if (document == null) {
            return Collections.emptyList();
        }

        Element messageItem = (Element) document.getElementsByTagName(TAG_NAME_MESSAGES).item(0);
        NodeList messages = messageItem.getElementsByTagName(TAG_NAME_MESSAGE);

        List<Message> result = new ArrayList<>();
        for (int i = 0; i < messages.getLength(); i++) {
            if (!(messages.item(i) instanceof Element)) {
                continue;
            }
            Element message = (Element) messages.item(i);
            String userFrom = retrieveByTagNameForMessage(message, TAG_NAME_USER_FROM);
            Timestamp timestamp = Timestamp.valueOf(retrieveByTagNameForMessage(message, TAG_NAME_TIME_STAMP));
            String messageContent = retrieveByTagNameForMessage(message, TAG_NAME_MESSAGE_CONTENT);
            Status status = Status.valueOf(retrieveByTagNameForMessage(message, TAG_NAME_STATUS));
            result.add(new Message(userFrom, timestamp, messageContent, status));
        }
        return result;
    }

    public Document readXml() {
        File file = new File(System.getProperty(KEY_FOR_USER_DIR) + PATH_XML);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        builderFactory.setIgnoringElementContentWhitespace(true);
        try {
            return builderFactory.newDocumentBuilder().parse(file);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new ParseXmlException("Reading XML is error", e);
        }
    }

    private String retrieveByTagNameForMessage(Element message, String tagName) {
        NodeList elementsByTagName = message.getElementsByTagName(tagName);
        Node item = elementsByTagName.item(0);
        return item.getTextContent();
    }

    private String retrieveByTagNameForUser(Element user, String tagName) {
        NodeList elementsByTagName = user.getElementsByTagName(tagName);
        Node item = elementsByTagName.item(0);
        return item.getTextContent();
    }

    public void addNewMessage(Document document, String fromUser, String timestamp, String contentMessage, Status statusMessage) {
        NodeList root = document.getElementsByTagName(TAG_NAME_MESSAGES);
        Element message = document.createElement(TAG_NAME_MESSAGE);
        Element userFrom = document.createElement(TAG_NAME_USER_FROM);
        Element timeStamp = document.createElement(TAG_NAME_TIME_STAMP);
        Element messageContent = document.createElement(TAG_NAME_MESSAGE_CONTENT);
        Element status = document.createElement(ParseXml.TAG_NAME_STATUS);
        userFrom.setTextContent(fromUser);
        timeStamp.setTextContent(timestamp);
        messageContent.setTextContent(contentMessage);
        status.setTextContent(statusMessage.name());
        message.appendChild(userFrom);
        message.appendChild(timeStamp);
        message.appendChild(status);
        message.appendChild(messageContent);
        root.item(0).appendChild(message);
        writeDocument(document);
    }

    public void removeMessageAboutKick(Document document, String name) {
        NodeList root = document.getElementsByTagName(TAG_NAME_MESSAGES);
        Element messageItem = (Element) document.getElementsByTagName(TAG_NAME_MESSAGES).item(0);
        NodeList messages = messageItem.getElementsByTagName(TAG_NAME_MESSAGE);
        Node node = null;
        for (int i = 0; i < messages.getLength(); i++) {
            Element message = (Element) messages.item(i);
            if (!retrieveByTagNameForMessage(message, TAG_NAME_MESSAGE_CONTENT).equals(name)) {
                continue;
            }
            if (!retrieveByTagNameForMessage(message, TAG_NAME_STATUS).equals(Status.KICK.toString())) {
                continue;
            }
            node = messages.item(i);
            break;
        }
        if (node == null) {
            LOGGER.error("Node is null");
            return;
        }
        root.item(0).removeChild(node);
        writeDocument(document);
    }

    public void addNewUser(Document document, String name, Role role) {
        if (checkUserVerification(name)) return;
        NodeList root = document.getElementsByTagName(TAG_NAME_USERS);
        Element user = document.createElement(TAG_NAME_USER);
        Element userName = document.createElement(TAG_NAME_NAME);
        Element userRole = document.createElement(TAG_NAME_ROLE);
        userName.setTextContent(name);
        userRole.setTextContent(role.name());
        user.appendChild(userName);
        user.appendChild(userRole);
        root.item(0).appendChild(user);
        writeDocument(document);
    }

    private boolean checkUserVerification(String name) {
        List<User> userList = createListUser();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equals(name)) {
                LOGGER.info("User already exists");
                return true;
            }
        }
        return false;
    }

    private void writeDocument(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(System.getProperty(KEY_FOR_USER_DIR) + PATH_XML);
            StreamResult result = new StreamResult(fos);
            transformer.transform(source, result);
        } catch (TransformerException | IOException e) {
            throw new ParseXmlException("Writing XML is error", e);
        }
    }
}
