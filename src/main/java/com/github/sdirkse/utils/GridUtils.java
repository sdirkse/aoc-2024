package com.github.sdirkse.utils;

import java.util.Arrays;

public class GridUtils {


    public static char[][] createGrid(int height, int length) {
        return new char[height][length];
    }

    public static char[][] createGrid(int height, int length, char defaultChar) {
        char[][] grid = createGrid(height, length);

        for (char[] row : grid) {
            Arrays.fill(row, defaultChar);
        }

        return grid;
    }

    public static int[][] createIntGrid(int height, int length, int defaultNumber) {
        int[][] grid = new int[height][length];

        for (int[] row : grid) {
            Arrays.fill(row, defaultNumber);
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

    public static void drawIntGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int n : row) {
                System.out.printf("%s", n);
            }
            System.out.println();
        }
    }

}
