package com.github.sdirkse;

import org.junit.jupiter.api.Test;
import com.github.sdirkse.utils.FileResourceUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayDAY_NUMBERTest {

    private final DayDAY_NUMBER riddle = new DayDAY_NUMBER();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("dayDAY_NUMBER.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(0, result);
    }

    @Test
    void testExamplePartOne() {
        String input = """
                """;

        long result = riddle.partOne(input);

        assertEquals(0, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(0, result);
    }

    @Test
    void testExamplePartTwo() {
        String input = """
                """;

        long result = riddle.partTwo(input);

        assertEquals(0, result);
    }

}