package com.epam.java_basic.calculator;


import java.util.Scanner;

public class CalculatorController {
    public static final char YES = 'Y';
    public static final char NO = 'N';
    private final Scanner scanner = new Scanner(System.in);
    private final CalculatorView calculatorView = new CalculatorView();
    private final Calculator calculator = new Calculator(2);


    public void start() {
        boolean calculatorStart = true;
        while (calculatorStart) {
            calculatorView.printMessageFirstNumber();
            double first = scanner.nextDouble();
            calculatorView.printMessageSecondNumber();
            double second = scanner.nextDouble();
            calculatorView.printMessageOperator();
            char operator = scanner.next().charAt(0);
            switch (operator) {
                case '+':
                    calculatorView.printResult(calculator.add(first, second));
                    break;
                case '-':
                    calculatorView.printResult(calculator.subtract(first, second));
                    break;
                case '*':
                    calculatorView.printResult(calculator.multiply(first, second));
                    break;
                case '/':
                    calculatorView.printResult(calculator.div(first, second));
                    break;
                default:
                    calculatorView.printCorrectOperator();
            }
            calculatorView.printContinue();
            char calculatorContinue = scanner.next().charAt(0);
            if (calculatorContinue == NO) {
                calculatorView.printBye();
                calculatorStart = false;
            }
            if (calculatorContinue == YES) {
                calculatorStart = true;
            }
        }
    }
}
