package com.github.sdirkse;

import com.github.sdirkse.utils.FileResourceUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    private final Day12 riddle = new Day12();
    private final FileResourceUtil fileResourceUtil = new FileResourceUtil();
    private final String input = fileResourceUtil.readFile("day12.txt");

    @Test
    void testPartOne() {
        long result = riddle.partOne(input);

        assertEquals(1344578, result);
    }

    @Test
    void testExample1PartOne() {
        String input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;

        long result = riddle.partOne(input);

        assertEquals(140, result);
    }

    @Test
    void testExample2PartOne() {
        String input = """
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
                """;

        long result = riddle.partOne(input);

        assertEquals(772, result);
    }

    @Test
    void testExample3PartOne() {
        String input = """
                RRRRIICCFF
                RRRRIICCCF
                VVRRRCCFFF
                VVRCCCJFFF
                VVVVCJJCFE
                VVIVCCJJEE
                VVIIICJJEE
                MIIIIIJJEE
                MIIISIJEEE
                MMMISSJEEE
                """;

        long result = riddle.partOne(input);

        assertEquals(1930, result);
    }

    @Test
    void testPartTwo() {
        long result = riddle.partTwo(input);

        assertEquals(814302, result);
    }

    @Test
    void testExample1PartTwo() {
        String input = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;

        long result = riddle.partTwo(input);

        assertEquals(80, result);
    }

    @Test
    void testExample2PartTwo() {
        String input = """
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
                """;

        long result = riddle.partTwo(input);

        assertEquals(436, result);
    }

    @Test
    void testExample3PartTwo() {
        String input = """
                AAAAAA
                AAABBA
                AAABBA
                ABBAAA
                ABBAAA
                AAAAAA
                """;

        long result = riddle.partTwo(input);

        assertEquals(368, result);
    }

}