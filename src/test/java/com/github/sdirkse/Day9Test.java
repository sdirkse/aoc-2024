package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    private final Day9 riddle = new Day9();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day9.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(6346871685398L, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                2333133121414131402
                """;

        long result = riddle.partOne(input);

        assertEquals(1928, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(6373055193464L, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                2333133121414131402
                """;

        long result = riddle.partTwo(input);

        assertEquals(2858, result);
    }

}