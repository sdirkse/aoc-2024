package com.github.sdirkse;

import org.junit.jupiter.api.Test;
import com.github.sdirkse.utils.FileResourceUtil;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    private final Day7 riddle = new Day7();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day7.txt");

    @Test
    void testPartOne() {
        BigInteger result = riddle.partOne(input);

        assertEquals(BigInteger.valueOf(Long.parseLong("4122618559853")), result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;

        BigInteger result = riddle.partOne(input);

        assertEquals(BigInteger.valueOf(3749), result);
    }

    @Test
    void testPartTwo() {
        BigInteger result = riddle.partTwo(input);

        assertEquals(BigInteger.valueOf(Long.parseLong("227615740238334")), result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;

        BigInteger result = riddle.partTwo(input);

        assertEquals(new BigInteger("11387"), result);
    }

    @Test
    void testWeirdCase() {
        String input = """
                39296223432: 7 859 2 43 10 5 84 32 2
                """;

        BigInteger result = riddle.partTwo(input);

        assertEquals(new BigInteger("0"), result);
    }

}