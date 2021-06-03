package com.epam.java_basic.string_processor;

import java.util.Scanner;

public class StringController {
    private final StringProcessor stringProcessor = new StringProcessor();
    private final StringView stringView = new StringView();
    private final Scanner scanner = new Scanner(System.in);

    void arrayInitialization(String[] array) {
        for (int i = 0; i < array.length; i++) {
            stringView.printInputLine(i);
            array[i] = scanner.next();
        }
    }

    void arrayWordInitialization(String[] array) {
        for (int i = 0; i < array.length; i++) {
            stringView.printInputWord(i);
            array[i] = scanner.next();
        }
    }

    public void start() {
        stringView.printMessageLines();
        int lengthStringArray = scanner.nextInt();
        String[] input = new String[lengthStringArray];
        arrayInitialization(input);
        stringView.printFindShortestLine(stringProcessor.findShortestLine(input));
        stringView.printFindLongestLine(stringProcessor.findLongestLine(input));
        stringView.printFindLinesShorterThanAverageLength(stringProcessor.findLinesShorterThanAverageLength(input));
        stringView.printFindLinesLongerThanAverageLength(stringProcessor.findLinesLongerThanAverageLength(input));
        stringView.printMessageWord();
        int lengthStringWordArray = scanner.nextInt();
        String[] inputWord = new String[lengthStringWordArray];
        arrayWordInitialization(inputWord);
        stringView.printFindWordWithMinimumVariousCharacters(stringProcessor.findWordWithMinimumVariousCharacters(inputWord));
        stringView.printFindWordConsistingOfVariousCharacters(stringProcessor.findWordConsistingOfVariousCharacters(inputWord));
        stringView.printFindWordConsistingOfDigits(stringProcessor.findWordConsistingOfDigits(inputWord));
    }
}
