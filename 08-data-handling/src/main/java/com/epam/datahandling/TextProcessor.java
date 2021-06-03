package com.epam.datahandling;

import com.epam.datahandling.lexis.Text;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

public class TextProcessor {

    public Text parse(File src) throws IOException {
        return new Text(Files.readString(src.toPath()));
    }
}
