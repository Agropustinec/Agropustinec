package com.epam.java_basic.string_processor;

/**
 * Useful methods for string processing
 */
public class StringProcessor {
    static final int BEGINNING_OF_RANGE = 48;
    static final int END_OF_RANGE = 57;
    static final int CODE = 9999;


    public String findShortestLine(String[] lines) {
        String veryShortString = lines[0];
        for (String line : lines) {
            if (veryShortString.length() > line.length()) {
                veryShortString = line;
            }
        }
        return veryShortString;
    }

    public String findLongestLine(String[] lines) {
        String veryLongString = lines[0];
        for (String line : lines) {
            if (veryLongString.length() < line.length()) {
                veryLongString = line;
            }
        }
        return veryLongString;
    }

    public String[] findLinesShorterThanAverageLength(String[] lines) {
        int numberOfCharacters = 0;
        int lengthReturnedArray = 0;
        int indexReturnedArray = 0;
        double averageLineLength;
        for (String s : lines) {
            numberOfCharacters += s.length();
        }
        averageLineLength = (double) numberOfCharacters / lines.length;
        for (String line : lines) {
            if (line.length() < averageLineLength) {
                lengthReturnedArray++;
            }
        }
        String[] returnedArray = new String[lengthReturnedArray];
        for (String line : lines) {
            if (line.length() < averageLineLength) {
                returnedArray[indexReturnedArray] = line;
                indexReturnedArray++;
            }
        }
        return returnedArray;
    }

    public String[] findLinesLongerThanAverageLength(String[] lines) {
        int numberOfCharacters = 0;
        int lengthReturnedArray = 0;
        int indexReturnedArray = 0;
        double averageLineLength;
        for (String s : lines) {
            numberOfCharacters += s.length();
        }
        averageLineLength = (double) numberOfCharacters / lines.length;
        for (String line : lines) {
            if (line.length() > averageLineLength) {
                lengthReturnedArray++;
            }
        }
        String[] returnedArray = new String[lengthReturnedArray];
        for (String line : lines) {
            if (line.length() > averageLineLength) {
                returnedArray[indexReturnedArray] = line;
                indexReturnedArray++;
            }
        }
        return returnedArray;
    }

    /**
     * Find word with minimum various characters. Return first word if there are a few of such words.
     *
     * @param words Input array of words
     * @return First word that consist of minimum amount of various characters
     */
    public String findWordWithMinimumVariousCharacters(String[] words) {
        int[] wordDifferense = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length() - 1; j++) {
                if (words[i].charAt(j) != words[i].charAt(j + 1)) {
                    wordDifferense[i]++;
                }
            }
        }
        int indexMinimumVariousCharacters = wordDifferense[0];
        for (int i = 0; i < wordDifferense.length; i++) {
            if (indexMinimumVariousCharacters > wordDifferense[i]) {
                indexMinimumVariousCharacters = i;
            }
        }
        return words[indexMinimumVariousCharacters];
    }

    /**
     * Find word containing only of various characters. Return first word if there are a few of such words.
     *
     * @param words Input array of words
     * @return First word that containing only of various characters
     */
    public String findWordConsistingOfVariousCharacters(String[] words) {
        int[] wordDifferense = new int[words.length];
        int indexWordDifferense = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length() - 1; j++) {
                if (words[i].charAt(j) != words[i].charAt(j + 1)) {
                    wordDifferense[i]++;
                }
            }
            wordDifferense[i]++;
        }
        for (int i = 0; i < words.length; i++) {
            if (wordDifferense[i] == words[i].length()) {
                indexWordDifferense = i;
                break;
            }
        }
        return words[indexWordDifferense];
    }

    /**
     * Find word containing only of digits. Return second word if there are a few of such words.
     *
     * @param words Input array of words
     * @return Second word that containing only of digits
     */

    public String findWordConsistingOfDigits(String[] words) {
        int[] numberOfDigitsInWord = helpFindWordConsistingOfDigits(words);
        int[] indexNumberOfDigitsInWord = new int[words.length];
        int indexIndexNumberOfDigitsInWord = 0;
        String returned = null;
        for (int i = 0; i < words.length; i++) {
            if (numberOfDigitsInWord[i] == words[i].length()) {
                indexNumberOfDigitsInWord[indexIndexNumberOfDigitsInWord] = i;
                if (i == 0) {
                    indexNumberOfDigitsInWord[indexIndexNumberOfDigitsInWord] = CODE; //coding Zero
                }
                indexIndexNumberOfDigitsInWord++;
            }
        }
        if (indexNumberOfDigitsInWord[1] != 0) {
            returned = words[indexNumberOfDigitsInWord[1]];
        } else {
            if (indexNumberOfDigitsInWord[0] != 0) {
                if (indexNumberOfDigitsInWord[0] == CODE) {
                    indexNumberOfDigitsInWord[0] = 0;
                    returned = words[indexNumberOfDigitsInWord[0]];
                } else {
                    returned = words[indexNumberOfDigitsInWord[0]];
                }
            }
        }
        return returned;
    }

    public int[] helpFindWordConsistingOfDigits(String[] words) {
        int[] numberOfDigitsInWord = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].charAt(j) >= BEGINNING_OF_RANGE && words[i].charAt(j) <= END_OF_RANGE) {
                    numberOfDigitsInWord[i]++;
                }
            }
        }
        return numberOfDigitsInWord;
    }
}

