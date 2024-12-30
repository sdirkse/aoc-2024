package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {

    private final Day22 riddle = new Day22();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day22.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(0, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                1
                10
                100
                2024
                """;

        long result = riddle.partOne(input);

        assertEquals(37327623, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(0, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                1
                2
                3
                2024
                """;

        long result = riddle.partTwo(input);

        assertEquals(23, result);
    }

}