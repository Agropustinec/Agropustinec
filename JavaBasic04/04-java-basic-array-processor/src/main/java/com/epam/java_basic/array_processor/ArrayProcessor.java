package com.epam.java_basic.array_processor;

/**
 * Useful methods for array processing
 */
public class ArrayProcessor {
    static final int PARITY_CHECK = 2;
    static final int MULTIPLIER_BY_THREE = 3;
    static final int CODE = 9999;

    public int[] swapMaxNegativeAndMinPositiveElements(int[] input) {
        int[] bufArray = new int[input.length];
        System.arraycopy(input, 0, bufArray, 0, bufArray.length);
        int maxNegative = bufArray[0];
        int minPositive = bufArray[0];
        int indexMaxNegative = 0;
        int indexMinPositive = 0;
        int tmp;
        for (int j : bufArray) {
            if (maxNegative > j) {
                maxNegative = j;
            }
            if (minPositive < j) {
                minPositive = j;
            }

        }
        for (int i = 0; i < bufArray.length; i++) {
            if (bufArray[i] < 0 && maxNegative < bufArray[i]) {
                maxNegative = bufArray[i];
                indexMaxNegative = i;
            }
            if (bufArray[i] > 0 && minPositive > bufArray[i]) {
                minPositive = bufArray[i];
                indexMinPositive = i;
            }


        }
        tmp = bufArray[indexMaxNegative];
        bufArray[indexMaxNegative] = bufArray[indexMinPositive];
        bufArray[indexMinPositive] = tmp;
        return bufArray;
    }

    public int countSumOfElementsOnEvenPositions(int[] input) {
        int sumOfEven = 0;
        for (int i = 0; i < input.length; i++) {
            if (i % PARITY_CHECK == 0) {
                sumOfEven += input[i];
            }
        }
        return sumOfEven;
    }

    public int[] replaceEachNegativeElementsWithZero(int[] input) {
        int[] bufArray = new int[input.length];
        System.arraycopy(input, 0, bufArray, 0, bufArray.length);
        for (int i = 0; i < bufArray.length; i++) {
            if (bufArray[i] < 0) {
                bufArray[i] = 0;
            }
        }
        return bufArray;
    }

    public int[] multiplyByThreeEachPositiveElementStandingBeforeNegative(int[] input) {
        int[] bufArray = new int[input.length];
        System.arraycopy(input, 0, bufArray, 0, bufArray.length);
        for (int i = 0; i < bufArray.length; i++) {
            if (i == bufArray.length - 1) {
                break;
            }
            if (bufArray[i] > 0 && bufArray[i + 1] < 0) {
                bufArray[i] = bufArray[i] * MULTIPLIER_BY_THREE;
            }
        }
        return bufArray;
    }

    public float calculateDifferenceBetweenAverageAndMinElement(int[] input) {
        float average;
        int minElement = input[0];
        int sumOfElements = 0;
        for (int j : input) {
            if (minElement > j) {
                minElement = j;
            }
            sumOfElements += j;
        }
        average = (float) sumOfElements / input.length - minElement;


        return average;
    }


    public int[] findSameElementsStandingOnOddPositions(int[] input) {
        int[] workingArray = fillingworkingArray(input);
        for (int i = 0; i < workingArray.length; i++) {
            for (int j = i; j < workingArray.length; j++) {
                if (workingArray[i] == workingArray[j] && i != j) {
                    workingArray[j] = 0;
                }
            }
        }
        int count = 0;
        for (int j : workingArray) {
            if (j != 0) {
                count++;
            }
        }
        return fillingReturnedArray(count, workingArray);
    }

    public int[] fillingworkingArray(int[] input) {
        int[] workingArray = new int[input.length];
        int indexWorkingArray = 0;

        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                if (input[i] == input[j] && i != j && (i % PARITY_CHECK == 1 || j % PARITY_CHECK == 1)) {
                    if (input[i] == 0) {
                        input[i] = CODE;
                    }
                    workingArray[indexWorkingArray] = input[i];
                    indexWorkingArray++;
                    if (input[i] == CODE) {
                        input[i] = 0;
                    }
                }
            }
        }
        return workingArray;
    }

    public int[] fillingReturnedArray(int count, int[] workingArray) {
        int[] returnedArray = new int[count];
        int indexReturnedArray = 0;
        for (int j : workingArray) {
            if (j != 0) {
                if (j == CODE) {
                    j = 0;
                }
                returnedArray[indexReturnedArray] = j;
                indexReturnedArray++;
            }
        }
        return returnedArray;

    }
}
