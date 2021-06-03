package com.epam.collections.lines_processor.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class FileReader {
    private static final Logger LOGGER = LogManager.getLogger(FileReader.class);

    public String readFile(String src) {
        String returnedLineString = null;
        try {
            File file = new File(src);
            returnedLineString = Files.readString(file.toPath());
        } catch (IOException e) {
            LOGGER.error("Error has occurred in readFile", e);
        }
        return returnedLineString;
    }

    public Set<String> createSetStringFromFile(String readFile) {
        String[] strings = readFile.split("\r\n");
        return new TreeSet<>(Arrays.asList(strings));
    }
}
