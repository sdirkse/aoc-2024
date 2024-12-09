package com.github.sdirkse;

import org.junit.jupiter.api.Test;
import com.github.sdirkse.utils.FileResourceUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    private final Day4 riddle = new Day4();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day4.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(2454, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;

        long result = riddle.partOne(input);

        assertEquals(18, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(1858, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """;

        long result = riddle.partTwo(input);

        assertEquals(9, result);
    }

}