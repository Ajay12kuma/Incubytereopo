package com.incubytes.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorControllerTest {

    @Test
    void testAddEmptyString() {
        StringCalculatorController calculator = new StringCalculatorController();
        assertEquals(0, calculator.add(""));
    }

    @Test
    void testAddSingleNumber() {
        StringCalculatorController calculator = new StringCalculatorController();
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void testAddTwoNumbers() {
        StringCalculatorController calculator = new StringCalculatorController();
        assertEquals(6, calculator.add("1,5"));
    }

    @Test
    void testAddNewLinesBetweenNumbers() {
        StringCalculatorController calculator = new StringCalculatorController();
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void testAddWithCustomDelimiter() {
        StringCalculatorController calculator = new StringCalculatorController();
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void testAddWithNegativeNumber() {
        StringCalculatorController calculator = new StringCalculatorController();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> calculator.add("-1,2,-3"));
        assertEquals("Negative numbers not allowed: -1, -3", exception.getMessage());
    }

    @Test
    void testAddWithNumbersGreaterThan1000() {
        StringCalculatorController calculator = new StringCalculatorController();
        assertEquals(5, calculator.add("2,1001,3"));
    }
}
