package com.github.sdirkse.utils;

import java.util.Arrays;

public class GridUtils {


    public static char[][] createGrid(int width, int length) {
        return new char[width][length];
    }

    public static char[][] createGrid(int width, int length, char defaultChar) {
        char[][] grid = createGrid(width, length);

        for (char[] row : grid) {
            Arrays.fill(row, defaultChar);
        }

        return grid;
    }

    public static void drawGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.printf("%s", c);
            }
            System.out.println();
        }
    }

}
