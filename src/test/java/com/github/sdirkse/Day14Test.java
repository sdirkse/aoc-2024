package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    private final Day14 riddle = new Day14();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day14.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input, 101, 103);

        assertEquals(231019008, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                p=0,0 v=1,3
                p=3,0 v=-2,-2
                p=7,6 v=-1,-3
                p=3,0 v=-1,-2
                p=9,3 v=2,3
                p=7,3 v=-1,2
                p=2,4 v=2,-3
                p=9,5 v=-3,-3
                """;

        long result = riddle.partOne(input, 11, 7);

        assertEquals(12, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input, 101, 103);

        assertEquals(42206220, result);
    }

}