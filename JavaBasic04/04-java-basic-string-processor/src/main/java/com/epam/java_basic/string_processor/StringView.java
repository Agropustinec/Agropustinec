package com.epam.java_basic.string_processor;

public class StringView {
    public static final String LENGTH = "(length is ";
    public static final String QUOTATION_MARK = ")";
    public static final String COLON = ":";
    public static final String NUMBER_OF_LINES = "Specify amount of lines:";
    public static final String NUMBER_OF_WORDS = "Specify amount of word:";
    public static final String SHORTEST_LINE = "Shortest line is";
    public static final String LONGEST_LINE = "Longest line is";
    public static final String LONGER_MEDIUM_LENGTH = "Lines longer than average length:";
    public static final String SHORTER_MEDIUM_LENGTH = "Lines shorter than average length:";
    public static final String MINIMUM_NUMBER_OF_DIFFERENT = "Word with minimum various characters: ";
    public static final String DIFFERENT_SYMBOLS_ONLY = "Word contains only various characters: ";
    public static final String ONLY_NUMBERS = "Word contains only digits: ";
    public static final String INPUT_LINE = "Input line ";
    public static final String INPUT_WORD = "Input Word ";


    void printMessageLines() {
        System.out.println(NUMBER_OF_LINES);
    }

    void printMessageWord() {
        System.out.println(NUMBER_OF_WORDS);
    }

    void printFindShortestLine(String str) {
        System.out.println(SHORTEST_LINE + str + " " + LENGTH + str.length() + QUOTATION_MARK);
    }

    void printFindLongestLine(String str) {
        System.out.println(LONGEST_LINE + str + LENGTH + str.length() + QUOTATION_MARK);
    }

    void printFindLinesShorterThanAverageLength(String[] strings) {
        System.out.println(LONGER_MEDIUM_LENGTH);
        for (String string : strings) {
            System.out.println(string + LENGTH + string.length() + QUOTATION_MARK);

        }
    }

    void printFindLinesLongerThanAverageLength(String[] strings) {
        System.out.println(SHORTER_MEDIUM_LENGTH);
        for (String string : strings) {
            System.out.println(string + LENGTH + string.length() + QUOTATION_MARK);

        }
    }

    void printFindWordWithMinimumVariousCharacters(String str) {
        System.out.println(MINIMUM_NUMBER_OF_DIFFERENT + str);
    }

    void printFindWordConsistingOfVariousCharacters(String str) {
        System.out.println(DIFFERENT_SYMBOLS_ONLY + str);
    }

    void printFindWordConsistingOfDigits(String str) {
        System.out.println(ONLY_NUMBERS + str);
    }

    void printInputLine(int i) {
        System.out.println(INPUT_LINE + i + COLON);
    }

    void printInputWord(int i) {
        System.out.println(INPUT_WORD + i + COLON);
    }
}
