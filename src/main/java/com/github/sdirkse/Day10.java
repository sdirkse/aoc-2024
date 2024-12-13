package com.github.sdirkse;

import com.github.sdirkse.utils.GridUtils;

class Day10 {

    private final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = GridUtils.createGrid(lines.length, lines[0].length());

        for (int x = 0; x < lines.length; x++) {
            for (int y = 0; y < lines[x].length(); y++) {
                grid[x][y] = lines[x].charAt(y);
            }
        }

        long sum = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == '0') {
                    sum += calculateTrailheadScore(grid, x, y, 0, GridUtils.createGrid(grid.length, grid[0].length, '.'));
                }
            }
        }

        return sum;
    }

    private long calculateTrailheadScore(char[][] grid, int x, int y, int nextHeight, char[][] visited) {
        long score = 0;

        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return 0;
        }
        if (visited[x][y] != '.') {
            return 0;
        }
        if (Integer.parseInt(String.valueOf(grid[x][y])) != nextHeight) {
            return 0;
        }
        if (grid[x][y] == '9') {
            visited[x][y] = grid[x][y];
            return 1;
        }

        visited[x][y] = grid[x][y];

        for (int[] direction : direction) {
            score += calculateTrailheadScore(grid, x + direction[0], y + direction[1], nextHeight + 1, visited);
        }

        return score;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = GridUtils.createGrid(lines.length, lines[0].length());

        for (int x = 0; x < lines.length; x++) {
            for (int y = 0; y < lines[x].length(); y++) {
                grid[x][y] = lines[x].charAt(y);
            }
        }

        long sum = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == '0') {
                    sum += calculateTrailheadRating(grid, x, y, 0);
                }
            }
        }

        return sum;
    }

    private long calculateTrailheadRating(char[][] grid, int x, int y, int nextHeight) {
        long score = 0;

        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return 0;
        }
        if (Integer.parseInt(String.valueOf(grid[x][y])) != nextHeight) {
            return 0;
        }
        if (grid[x][y] == '9') {
            return 1;
        }

        for (int[] direction : direction) {
            score += calculateTrailheadRating(grid, x + direction[0], y + direction[1], nextHeight + 1);
        }

        return score;
    }

}