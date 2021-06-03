package com.epam.java_basic;

import com.epam.java_basic.calculator.CalculatorController;

/**
 * Denis Varin
 * 04 Java Basic - Calculator
 */
public class Application {

    public static void main(String[] args) {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.start();
    }
}
