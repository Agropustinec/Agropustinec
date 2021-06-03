package com.epam.java_basic.array_processor;


import java.util.Scanner;

public class ArrayController {
    static final int UPPER_RANGE_OF_ARRAY_FILLING = 20;
    static final int LOWER_RANGE_OF_ARRAY_FILLING = 10;
    static final int ARRAY_SIZE = 10;
    static final int ACTION_WITH_THE_ARRAY_ONE = 1;
    static final int ACTION_WITH_THE_ARRAY_TWO = 2;
    static final int ACTION_WITH_THE_ARRAY_THREE = 3;
    static final int ACTION_WITH_THE_ARRAY_FOUR = 4;
    static final int ACTION_WITH_THE_ARRAY_FIVE = 5;
    static final int ACTION_WITH_THE_ARRAY_SIX = 6;
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayProcessor arrayProcessor = new ArrayProcessor();
    private final ArrayView arrayView = new ArrayView();

    double[] createArray() {
        double[] array = new double[ARRAY_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = (Math.random() * UPPER_RANGE_OF_ARRAY_FILLING) - LOWER_RANGE_OF_ARRAY_FILLING;
        }
        return array;
    }

    int[] convertArray() {
        double[] arr = createArray();
        int[] array = new int[arr.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) arr[i];
        }
        return array;
    }


    public void start() {
        int[] input = convertArray();
        int[] bufArray;
        boolean start = true;
        while (start) {
            arrayView.printMenu();
            arrayView.printArray(input);
            int choice = scanner.nextInt();
            switch (choice) {
                case ACTION_WITH_THE_ARRAY_ONE:
                    bufArray = arrayProcessor.swapMaxNegativeAndMinPositiveElements(input);
                    arrayView.printArray(bufArray);
                    break;
                case ACTION_WITH_THE_ARRAY_TWO:
                    int returnedMethodInt = arrayProcessor.countSumOfElementsOnEvenPositions(input);
                    arrayView.printResult(returnedMethodInt);
                    break;
                case ACTION_WITH_THE_ARRAY_THREE:
                    bufArray = arrayProcessor.replaceEachNegativeElementsWithZero(input);
                    arrayView.printArray(bufArray);
                    break;
                case ACTION_WITH_THE_ARRAY_FOUR:
                    bufArray = arrayProcessor.multiplyByThreeEachPositiveElementStandingBeforeNegative(input);
                    arrayView.printArray(bufArray);
                    break;
                case ACTION_WITH_THE_ARRAY_FIVE:
                    float returnedMethodFloat = arrayProcessor.calculateDifferenceBetweenAverageAndMinElement(input);
                    arrayView.printResult(returnedMethodFloat);
                    break;
                case ACTION_WITH_THE_ARRAY_SIX:
                    bufArray = arrayProcessor.findSameElementsStandingOnOddPositions(input);
                    arrayView.printArray(bufArray);
                    break;
                default:
                    break;

            }
            start = false;
        }
    }
}