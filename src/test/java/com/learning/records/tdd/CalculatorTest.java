package com.learning.records.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void addTwoDigits(){
        Calculator calculator = new Calculator();

        int result = calculator.add(6, 4);

        assertEquals(10, result);
    }
}
