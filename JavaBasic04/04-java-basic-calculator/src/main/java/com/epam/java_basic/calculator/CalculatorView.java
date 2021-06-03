package com.epam.java_basic.calculator;

public class CalculatorView {
    public static final String ENTER_FIRST_NUMBER = "Enter the first number:";
    public static final String ENTER_SECOND_NUMBER = "Enter the second number:";
    public static final String OPERATOR = "Enter operator (+, -, *, /):";
    public static final String RESULT = "Result: ";
    public static final String CONTINUE = "Do you want to continue? (Y/N)";
    public static final String BYE = "Bye";
    public static final String ENTER_CORRECT_OPERATOR = "Please enter the correct operator";


    void printMessageFirstNumber() {
        System.out.println(ENTER_FIRST_NUMBER);
    }

    void printMessageSecondNumber() {
        System.out.println(ENTER_SECOND_NUMBER);
    }

    void printMessageOperator() {
        System.out.println(OPERATOR);
    }

    void printResult(double result) {
        System.out.println(RESULT + result);
    }

    void printContinue() {
        System.out.print(CONTINUE);
    }

    void printBye() {
        System.out.println(BYE);
    }

    void printCorrectOperator() {
        System.out.println(ENTER_CORRECT_OPERATOR);
    }
}
