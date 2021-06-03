package com.epam.java_basic.calculator;

public class Calculator {
    static final int DIGIT_5 = 5;
    private int precision;

    public Calculator(int precision) {
        this.precision = precision;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        double result = a * b;
        return rounding(result);
    }

    public double div(double a, double b) {
        double result = 0;
        if (b == 0) {
            if (a > 0) {
                result = Double.POSITIVE_INFINITY;
            }
            if (a < 0) {
                result = Double.NEGATIVE_INFINITY;
            }
        } else {
            result = rounding(a / b);
        }
        return result;
    }

    public double rounding(double a) {
        double result;
        if (countDouble(a) == 0) result = a;

        if (countDouble(a) < precision) {
            precision = countDouble(a);
        }
        String str = Double.toString(a);
        StringBuilder strOfRounding = new StringBuilder(str);
        StringBuilder strUserMantissa = new StringBuilder();
        int lastElementOfNumber = strOfRounding.charAt(countInteger(a) + precision) - '0';
        try {
            int nextLastElementOfNumber = strOfRounding.charAt(countInteger(a) + precision + 1) - '0';
            if (nextLastElementOfNumber >= DIGIT_5) {
                lastElementOfNumber++;

            }
        } catch (Exception ex) {
            int nextLastElementOfNumber = 0;
        }

        strOfRounding.setCharAt(countInteger(a) + precision, (char) (lastElementOfNumber + '0'));
        for (int i = 0; i < countInteger(a) + precision + 1; i++) {
            strUserMantissa.append(strOfRounding.charAt(i));
        }

        String strl = strUserMantissa.toString();
        result = Double.parseDouble(strl);
        return result;
    }


    public int countInteger(double a) {
        int integerPart = 0;
        String strOfIntegerPart = Double.toString(a);
        for (int i = 0; i < strOfIntegerPart.length(); i++) {
            if (strOfIntegerPart.charAt(i) != '.') {
                integerPart++;
            } else {
                break;
            }


        }
        return integerPart;
    }

    public int countDouble(double a) {
        int doublePart = 0;
        String strOfDoublePart = Double.toString(a);
        for (int i = countInteger(a) + 1; i < strOfDoublePart.length(); i++) {
            doublePart++;
        }
        System.out.println(doublePart);
        return doublePart;
    }
}