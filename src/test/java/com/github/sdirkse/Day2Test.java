package com.github.sdirkse;

import org.junit.jupiter.api.Test;
import com.github.sdirkse.utils.FileResourceUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    private final Day2 riddle = new Day2();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day2.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(242, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """;

        long result = riddle.partOne(input);

        assertEquals(2, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(311, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """;

        long result = riddle.partTwo(input);

        assertEquals(4, result);
    }

}