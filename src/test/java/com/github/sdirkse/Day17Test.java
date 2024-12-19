package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    private final Day17 riddle = new Day17();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day17.txt");

    @Test
    void testPartOne() {
        String result = riddle.partOne(input);

        assertEquals("1,2,3,1,3,2,5,3,1", result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                Register A: 729
                Register B: 0
                Register C: 0
                                
                Program: 0,1,5,4,3,0
                """;

        String result = riddle.partOne(input);

        assertEquals("4,6,3,5,6,3,5,2,1,0", result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(105706277661082L, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                """;

        long result = riddle.partTwo(input);

        assertEquals(0, result);
    }

}