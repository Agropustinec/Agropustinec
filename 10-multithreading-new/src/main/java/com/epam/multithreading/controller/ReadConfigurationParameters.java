package com.epam.multithreading.controller;

import com.epam.multithreading.AppRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigurationParameters {
    private static final Logger LOGGER = LogManager.getLogger(ReadConfigurationParameters.class);
    private static final String PATH_TO_PROPERTIES = "application.properties";

    public long getTime(String key) {
        return Long.parseLong(readProperties(key));
    }

    public int getParameter(String key) {
        return Integer.parseInt(readProperties(key));
    }

    private String readProperties(String key) {
        Properties properties = new Properties();
        try (
                InputStream in = AppRunner.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES)) {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error("Can't load properties", e);
        }
        return properties.getProperty(key);
    }
}