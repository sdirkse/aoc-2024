package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    private final Day3 riddle = new Day3();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day3.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(184122457, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
                """;

        long result = riddle.partOne(input);

        assertEquals(161, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(107862689, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
                """;

        long result = riddle.partTwo(input);

        assertEquals(48, result);
    }

}