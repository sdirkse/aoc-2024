package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    private final Day8 riddle = new Day8();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day8.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(367, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............
                """;

        long result = riddle.partOne(input);

        assertEquals(14, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(1285, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............
                """;

        long result = riddle.partTwo(input);

        assertEquals(34, result);
    }

}