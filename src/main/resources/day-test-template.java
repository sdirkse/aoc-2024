package com.github.sdirkse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayDAY_NUMBERTest {

    private final DayDAY_NUMBER riddle = new DayDAY_NUMBER();

    @Test
    void testPartOne() {
        String input = "";

        long result = riddle.partOne(input);

        assertEquals(0, result);
    }

    @Test
    void testPartTwo() {
        String input = "";

        long result = riddle.partTwo(input);

        assertEquals(0, result);
    }

}