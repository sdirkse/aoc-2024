package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    private final Day13 riddle = new Day13();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day13.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(32067, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400
                                
                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176
                                
                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=7870, Y=6450
                                
                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=18641, Y=10279
                """;

        long result = riddle.partOne(input);

        assertEquals(480, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(92871736253789L, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400
                                
                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176
                                
                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=7870, Y=6450
                                
                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=18641, Y=10279
                """;

        long result = riddle.partTwo(input);

        assertEquals(875318608908L, result);
    }

}