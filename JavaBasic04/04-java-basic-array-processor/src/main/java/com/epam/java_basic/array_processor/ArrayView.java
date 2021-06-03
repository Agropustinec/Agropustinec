package com.epam.java_basic.array_processor;

public class ArrayView {
    static final String MENU_ITEM_ONE = "enter 1 to swap the maximum negative and minimum positive";
    static final String MENU_ITEM_TWO = "enter 2 to determine the sum of the elements in even positions";
    static final String MENU_ITEM_THREE = "enter 3 to replace negative elements with zeros";
    static final String MENU_ITEM_FOUR = "enter 4 to triple every positive element that comes before negative";
    static final String MENU_ITEM_FIVE = "enter 5 to find the difference between the arithmetic mean and the minimum element value";
    static final String MENU_ITEM_SIX = "enter 6 to display all elements that occur more than once and whose indices are odd";
    public void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public void printMenu() {
        System.out.println(MENU_ITEM_ONE);
        System.out.println(MENU_ITEM_TWO);
        System.out.println(MENU_ITEM_THREE);
        System.out.println(MENU_ITEM_FOUR);
        System.out.println(MENU_ITEM_FIVE);
        System.out.println(MENU_ITEM_SIX);

    }

    public void printResult(int a) {
        System.out.println(a);
    }

    public void printResult(float a) {
        System.out.println(a);
    }
}