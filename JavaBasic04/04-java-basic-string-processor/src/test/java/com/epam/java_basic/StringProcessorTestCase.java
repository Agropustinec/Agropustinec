package com.epam.java_basic;

import com.epam.java_basic.string_processor.StringProcessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class StringProcessorTestCase {

    @Test
    public void testFindShortestLine() {
        String[] input = new String[] {"qwerty", "qw", "qwerty qwerty"};
        String expectedResult = "qw";
        StringProcessor stringProcessor = new StringProcessor();
        String result;
        result = stringProcessor.findShortestLine(input);
        assertThat(String.format("Wrong result of method findShortestLine (input is %s)", Arrays.toString(input)), result, is(expectedResult));
    }

    @Test
    public void testFindLongestLine() {
        String[] input = new String[] {"qwerty", "qw", "qwerty qwerty"};
        String expectedResult = "qwerty qwerty";
        StringProcessor stringProcessor = new StringProcessor();
        String result = stringProcessor.findLongestLine(input);
        assertThat(String.format("Wrong result of method findLongestLine (input is %s)", Arrays.toString(input)), result, is(expectedResult));
    }

    @Test
    public void testFindLinesShorterThanAverageLength() {
        String[] input = new String[] {"qwerty", "qw", "qwerty qwerty"};
        String[] expectedResult = new String[] {"qw", "qwerty"};
        StringProcessor stringProcessor = new StringProcessor();
        String[] result = stringProcessor.findLinesShorterThanAverageLength(input);
        assertThat(String.format("Wrong result of method findLinesShorterThanAverageLength (input is %s)", Arrays.toString(input)), result, arrayContainingInAnyOrder(expectedResult));
    }

    @Test
    public void testFindLinesLongerThanAverageLength() {
        String[] input = new String[] {"qwerty", "qw", "qwerty qwerty"};
        String[] expectedResult = new String[] {"qwerty qwerty"};
        StringProcessor stringProcessor = new StringProcessor();
        String[] result = stringProcessor.findLinesLongerThanAverageLength(input);
        assertThat(String.format("Wrong result of method findLinesLongerThanAverageLength (input is %s)", Arrays.toString(input)), result, arrayContainingInAnyOrder(expectedResult));
    }

    @Test
    public void testFindWordWithMinimumVariousCharacters() {
        String[] input = new String[] {"qwer", "qqqqqqqqqqwe", "123", "4321"};
        String expectedResult = "qqqqqqqqqqwe";
        StringProcessor stringProcessor = new StringProcessor();
        String result = stringProcessor.findWordWithMinimumVariousCharacters(input);
        assertThat(String.format("Wrong result of method findWordWithMinimumVariousCharacters (input is %s)", Arrays.toString(input)), result, is(expectedResult));
    }

    @Test
    public void testFindWordConsistingOfVariousCharacters() {
        String[] input = new String[] {"qqqqqqqqqqwe", "qwer", "123", "4321"};
        String expectedResult = "qwer";
        StringProcessor stringProcessor = new StringProcessor();
        String result = stringProcessor.findWordConsistingOfVariousCharacters(input);
        assertThat(String.format("Wrong result of method findWordConsistingOfVariousCharacters (input is %s)", Arrays.toString(input)), result, is(expectedResult));
    }

    @Test
    public void testFindWordConsistingOfDigitsOnlyNullResult() {
        String[] input = new String[] {"qqqqqqqqqqwe", "qwer"};
        String expectedResult = null;
        StringProcessor stringProcessor = new StringProcessor();
        String result = stringProcessor.findWordConsistingOfDigits(input);
        assertThat(String.format("Wrong result of method findWordConsistingOfDigits (input is %s)", Arrays.toString(input)), result, is(expectedResult));
    }

    @Test
    public void testFindWordConsistingOfDigitsOnlyOneResult() {
        String[] input = new String[] {"qqqqqqqqqqwe", "qwer", "123"};
        String expectedResult = "123";
        StringProcessor stringProcessor = new StringProcessor();
        String result = stringProcessor.findWordConsistingOfDigits(input);
        assertThat(String.format("Wrong result of method findWordConsistingOfDigits (input is %s)", Arrays.toString(input)), result, is(expectedResult));
    }

    @Test
    public void testFindWordConsistingOfDigitsMoreThanOneResult() {
        String[] input = new String[] {"qqqqqqqqqqwe", "qwer", "123", "4321"};
        String expectedResult = "4321";
        StringProcessor stringProcessor = new StringProcessor();
        String result = stringProcessor.findWordConsistingOfDigits(input);
        assertThat(String.format("Wrong result of method findWordConsistingOfDigits (input is %s)", Arrays.toString(input)), result, is(expectedResult));
    }
}
