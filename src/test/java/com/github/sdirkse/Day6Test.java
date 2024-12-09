package com.github.sdirkse;

import org.junit.jupiter.api.Test;
import com.github.sdirkse.utils.FileResourceUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    private final Day6 riddle = new Day6();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day6.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(4722, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                """;

        long result = riddle.partOne(input);

        assertEquals(41, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(1602, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                """;

        long result = riddle.partTwo(input);

        assertEquals(6, result);
    }

}