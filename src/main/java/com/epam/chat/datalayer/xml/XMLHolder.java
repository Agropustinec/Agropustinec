package com.epam.chat.datalayer.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class XMLHolder {
    private final Document document;
    private final Logger LOGGER = LogManager.getLogger(XMLHolder.class);

    public XMLHolder(String schemaName, String xmlName) {
        Schema schema = loadSchema(schemaName);
        document = parseXmlDom(xmlName);
        validateXml(schema, document);
    }


    private Schema loadSchema(String schemaFileName) {
        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(resourceAsFile(schemaFileName));
        } catch (Exception e) {
           LOGGER.error("LoadSchema is error", e);
            throw new RuntimeException(e);
        }
        return schema;
    }

    private Document parseXmlDom(String xmlName) {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(resourceAsFile(xmlName));
        } catch (Exception e) {
            LOGGER.error("ParseXml is error", e);
            throw new RuntimeException(e);
        }

        return document;
    }

    private void validateXml(Schema schema, Document document) {
        try {
            Validator validator = schema.newValidator();
          LOGGER.info ("Validator Class: " + validator.getClass().getName());
            validator.validate(new DOMSource(document));
            LOGGER.info("Validation passed");
        } catch (Exception e) {
            LOGGER.error("ValidateXml is error", e);
            throw new RuntimeException(e);
        }
    }

    private File resourceAsFile(String name) {
        try {
            final URL resource = XMLHolder.class.getClassLoader().getResource(name);
            if (resource == null) {
                throw new IllegalArgumentException("Resource not found: " + name);
            }
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
