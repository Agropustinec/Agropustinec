package com.epam.chat.datalayer.xml;

public class ParseXmlException extends RuntimeException {
    public ParseXmlException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
