package com.epam.java_basic;

import com.epam.java_basic.calculator.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class CalculatorTestCase {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator(2);
        double a = 10.1;
        double b = 6.3;
        double result = calculator.add(a, b);
        double expectedResult = 16.4;
        assertThat(String.format("Wrong result of method add (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testAddPositiveOverload() {
        Calculator calculator = new Calculator(2);
        double a =  Double.MAX_VALUE;
        double b =  Double.MAX_VALUE;
        double result = calculator.add(a, b);
        double expectedResult = Double.POSITIVE_INFINITY;
        assertThat(String.format("Wrong result of method add (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testAddNegativeOverload() {
        Calculator calculator = new Calculator(2);
        double a =  Double.MAX_VALUE * -1;
        double b =  Double.MAX_VALUE * -1;
        double result = calculator.add(a, b);
        double expectedResult = Double.NEGATIVE_INFINITY;
        assertThat(String.format("Wrong result of method add (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator(2);
        double a = 10.1;
        double b = 6.3;
        double result = calculator.subtract(a, b);
        double expectedResult = 3.8;
        assertThat(String.format("Wrong result of method subtract (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testSubtractPositiveOverload() {
        Calculator calculator = new Calculator(2);
        double a =  Double.MAX_VALUE;
        double b =  Double.MAX_VALUE * -1;
        double result = calculator.subtract(Double.MAX_VALUE, Double.MAX_VALUE * -1);
        double expectedResult = Double.POSITIVE_INFINITY;
        assertThat(String.format("Wrong result of method subtract (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testSubtractNegativeOverload() {
        Calculator calculator = new Calculator(2);
        double a =  Double.MAX_VALUE * -1;
        double b =  Double.MAX_VALUE;
        double result = calculator.subtract(a, b);
        double expectedResult = Double.NEGATIVE_INFINITY;
        assertThat(String.format("Wrong result of method subtract (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testMultiply() {
        Calculator calculator = new Calculator(2);
        double a = 10.1;
        double b = 6.3;
        double result = calculator.multiply(a, b);
        double expectedResult = 63.63;
        assertThat(String.format("Wrong result of method multiply (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testDiv() {
        Calculator calculator = new Calculator(2);
        double a = 10.1;
        double b = 6.3;
        double result = calculator.div(a, b);
        double expectedResult = 1.6;
        assertThat(String.format("Wrong result of method div (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testDivPositiveByZero() {
        Calculator calculator = new Calculator(2);
        double a = 10.1;
        double b = 0;
        double result = calculator.div(a, b);
        double expectedResult = Double.POSITIVE_INFINITY;
        assertThat(String.format("Wrong result of method div (input is %f and %f)", a, b), result, is(expectedResult));
    }

    @Test
    public void testDivNegativeByZero() {
        Calculator calculator = new Calculator(2);
        double a = -10.1;
        double b = 0;
        double result = calculator.div(a, b);
        double expectedResult = Double.NEGATIVE_INFINITY;
        assertThat(String.format("Wrong result of method div (input is %f and %f)", a, b), result, is(expectedResult));
    }

}
