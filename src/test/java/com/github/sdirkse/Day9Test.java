package com.github.sdirkse;

import org.junit.jupiter.api.Test;
import com.github.sdirkse.utils.FileResourceUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    private final Day9 riddle = new Day9();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day9.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(Long.parseLong("6346871685398"), result);
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

        assertEquals(Long.parseLong("6373055193464"), result);
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