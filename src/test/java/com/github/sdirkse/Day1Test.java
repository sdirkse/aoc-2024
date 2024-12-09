package com.github.sdirkse;

import org.junit.jupiter.api.Test;
import com.github.sdirkse.utils.FileResourceUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    private final Day1 riddle = new Day1();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day1.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(1319616, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """;

        long result = riddle.partOne(input);

        assertEquals(11, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(27267728, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """;

        long result = riddle.partTwo(input);

        assertEquals(31, result);
    }

}