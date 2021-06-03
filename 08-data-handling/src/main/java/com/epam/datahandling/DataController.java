package com.epam.datahandling;

import com.epam.datahandling.lexis.Sentence;
import com.epam.datahandling.lexis.Text;
import com.epam.datahandling.utils.BackupUtils;
import com.epam.datahandling.utils.LengthComparator;
import com.epam.datahandling.utils.TableGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class DataController {
    private static final String LINE_BREAK = "\n";
    private static final String KEY_FOR_USER_DIR = "user.dir";
    private static final String PATH_TO_RESOURCES = "\\src\\main\\resources\\";
    private static final String PATH_TO_SERIALIZED_TEXT_FILE = PATH_TO_RESOURCES + "searilized-text-processor.ser";
    private static final String SRC = System.getProperty(KEY_FOR_USER_DIR) + PATH_TO_RESOURCES + "original\\book.txt";
    private static final String DEST = System.getProperty(KEY_FOR_USER_DIR) + PATH_TO_RESOURCES + "backup\\book.bak";
    private static final Logger LOGGER = LogManager.getLogger(DataController.class);
    private static final int NUMBER_COLUMN_WIDTH = 13;
    private static final int SENTENCE_COLUMN_WIDTH = 80;
    private static final int WORD_COLUMN_WIDTH = 13;

    void run(int maxDataLength) {
        try {
            BackupUtils.backup(SRC, DEST);
        } catch (IOException e) {
            LOGGER.error("Backup error", e);
        }
        File file = new File(DEST);
        TextProcessor textProcessor = new TextProcessor();

        try {
            Text text = textProcessor.parse(file);
            serializeContent(text);
            Text textSort = sortSentence(text);
            TableGenerator tableGenerator = new TableGenerator(NUMBER_COLUMN_WIDTH, SENTENCE_COLUMN_WIDTH, WORD_COLUMN_WIDTH);
            addSourceFile(SRC, tableGenerator.generate(textSort, maxDataLength));
        } catch (IOException e) {
            LOGGER.error("Parse working is error", e);
        }
    }

    Text sortSentence(Text text) {
        Sentence[] sentencesSort = text.getSentences();
        Arrays.sort(sentencesSort, new LengthComparator());
        StringBuilder builder = new StringBuilder();
        for (Sentence sentence : sentencesSort) {
            builder.append(sentence.getContent()).append(LINE_BREAK);
        }
        return new Text(builder.toString());
    }

    public void serializeContent(Text text) {
        try (FileOutputStream fileOut =
                     new FileOutputStream(System.getProperty(KEY_FOR_USER_DIR) + PATH_TO_SERIALIZED_TEXT_FILE);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(text);
        } catch (IOException e) {
            LOGGER.error("Serialization error", e);
        }
    }

    public static void addSourceFile(String src, String table) {
        if (new File(src).exists()) {
            appendUsingOutputStream(src, table);
        }

    }

    private static void appendUsingOutputStream(String fileName, String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(fileName), true);
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            LOGGER.error("Add file - Error", e);
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                LOGGER.error("One of streams cannot be closed", e);
            }
        }
    }
}