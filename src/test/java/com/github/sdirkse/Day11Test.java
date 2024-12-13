package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    private final Day11 riddle = new Day11();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day11.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(189547, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                125 17
                """;

        long result = riddle.partOne(input);

        assertEquals(55312, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(224577979481346L, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                125 17
                """;

        long result = riddle.partTwo(input);

        assertEquals(65601038650482L, result);
    }

}