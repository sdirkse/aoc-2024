package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    private final Day18 riddle = new Day18();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day18.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input, 71, 71);

        assertEquals(0, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                5,4
                4,2
                4,5
                3,0
                2,1
                6,3
                2,4
                1,5
                0,6
                3,3
                2,6
                5,1
                1,2
                5,5
                2,5
                6,5
                1,4
                0,4
                6,4
                1,1
                6,1
                1,0
                0,5
                1,6
                2,0
                """;

        long result = riddle.partOne(input, 7, 7);

        assertEquals(22, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input, 71, 71);

        assertEquals(0, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                """;

        long result = riddle.partTwo(input, 71, 71);

        assertEquals(0, result);
    }

}