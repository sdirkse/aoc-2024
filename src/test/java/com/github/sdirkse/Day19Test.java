package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {

    private final Day19 riddle = new Day19();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day19.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(338, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                r, wr, b, g, bwu, rb, gb, br
                                
                brwrr
                bggr
                gbbr
                rrbgbr
                ubwu
                bwurrg
                brgr
                bbrgwb
                """;

        long result = riddle.partOne(input);

        assertEquals(6, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(841533074412361L, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                r, wr, b, g, bwu, rb, gb, br
                                
                brwrr
                bggr
                gbbr
                rrbgbr
                ubwu
                bwurrg
                brgr
                bbrgwb
                """;

        long result = riddle.partTwo(input);

        assertEquals(16, result);
    }

}