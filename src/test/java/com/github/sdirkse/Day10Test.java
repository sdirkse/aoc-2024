package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    private final Day10 riddle = new Day10();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day10.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(496, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                89010123
                78121874
                87430965
                96549874
                45678903
                32019012
                01329801
                10456732
                """;

        long result = riddle.partOne(input);

        assertEquals(36, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(1120, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                1190119
                1111198
                1112117
                6543456
                7651987
                8761111
                9871111
                """;

        long result = riddle.partTwo(input);

        assertEquals(13, result);
    }

}