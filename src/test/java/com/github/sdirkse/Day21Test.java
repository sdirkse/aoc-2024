package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

    private final Day21 riddle = new Day21();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day21.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(270084, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                029A
                980A
                179A
                456A
                379A
                """;

        long result = riddle.partOne(input);

        assertEquals(126384, result);
    }

    @Nested
    class SeparateTestCases {
        @Test
        void test1() {
            String input = """
                    029A
                    """;

            long result = riddle.partOne(input);

            assertEquals(1972, result);
        }

        @Test
        void test2() {
            String input = """
                    980A
                    """;

            long result = riddle.partOne(input);

            assertEquals(58800, result);
        }

        @Test
        void test3() {
            String input = """
                    179A
                    """;

            long result = riddle.partOne(input);

            assertEquals(12172, result);
        }

        @Test
        void test4() {
            String input = """
                    456A
                    """;

            long result = riddle.partOne(input);

            assertEquals(29184, result);
        }

        @Test
        void test5() {
            String input = """
                    379A
                    """;

            long result = riddle.partOne(input);

            assertEquals(24256, result);
        }
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(0, result);
    }

}