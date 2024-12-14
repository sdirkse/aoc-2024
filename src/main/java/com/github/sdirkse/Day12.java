package com.github.sdirkse;

import com.github.sdirkse.utils.GridUtils;

import java.util.ArrayList;
import java.util.List;

class Day12 {

    private int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = GridUtils.createGrid(lines.length, lines[0].length());
        char[][] visitedGrid = GridUtils.createGrid(lines.length, lines[0].length(), ' ');

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        List<Plot> plots = new ArrayList<>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (visitedGrid[x][y] == ' ') {
                    char[][] plotGrid = GridUtils.createGrid(lines.length, lines[0].length(), ' ');
                    Tuple tuple = determinePlot(grid[x][y], x, y, grid, visitedGrid, plotGrid);
                    Plot plot = new Plot(grid[x][y], tuple.left(), tuple.right());

                    plots.add(plot);
                }
            }
        }

        return plots.stream().mapToLong(Plot::getCost).sum();
    }

    private Tuple determinePlot(char plantLetter, int x, int y, char[][] grid, char[][] visitedGrid, char[][] plotGrid) {
        if (x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1) {
            return new Tuple(0, 1);
        }
        if (grid[x][y] != plantLetter) {
            return new Tuple(0, 1);
        }
        if (visitedGrid[x][y] == 'X') {
            return new Tuple(0, 0);
        }

        visitedGrid[x][y] = 'X';
        plotGrid[x][y] = 'X';

        int plotSize = 1;
        int fenceCount = 0;

        for (int[] direction : directions) {
            Tuple tuple = determinePlot(plantLetter, x + direction[0], y + direction[1], grid, visitedGrid, plotGrid);
            plotSize += tuple.left();
            fenceCount += tuple.right();
        }

        return new Tuple(plotSize, fenceCount);
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = GridUtils.createGrid(lines.length, lines[0].length());
        char[][] visitedGrid = GridUtils.createGrid(lines.length, lines[0].length(), ' ');

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        List<Plot> plots = new ArrayList<>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (visitedGrid[x][y] == ' ') {
                    char[][] plotGrid = GridUtils.createGrid(lines.length, lines[0].length(), ' ');
                    Tuple tuple = determinePlot(grid[x][y], x, y, grid, visitedGrid, plotGrid);
                    long plotSides = determinePlotSides(plotGrid, tuple.left, tuple.right);

                    Plot plot = new Plot(grid[x][y], tuple.left(), plotSides);

                    plots.add(plot);
                }
            }
        }

        return plots.stream().mapToLong(Plot::getCost).sum();
    }

    private long determinePlotSides(char[][] plotGrid, long plotSize, long fenceCount) {
        for (int x = 0; x < plotGrid.length; x++) {
            for (int y = 0; y < plotGrid[0].length; y++) {
                if (plotGrid[x][y] == 'X') {
                    plotSize--;

                    if (y > 0 && plotGrid[x][y - 1] == 'X') {
                        if (hasTopFence(plotGrid, x, y) && hasTopFence(plotGrid, x, y - 1)) {
                            fenceCount--;
                        }
                        if (hasBottomFence(plotGrid, x, y) && hasBottomFence(plotGrid, x, y - 1)) {
                            fenceCount--;
                        }
                    }
                    if (x > 0 && plotGrid[x - 1][y] == 'X') {
                        if (hasLeftFence(plotGrid, x, y) && hasLeftFence(plotGrid, x - 1, y)) {
                            fenceCount--;
                        }
                        if (hasRightFence(plotGrid, x, y) && hasRightFence(plotGrid, x - 1, y)) {
                            fenceCount--;
                        }
                    }
                }
                if (plotSize == 0) {
                    break;
                }
            }
        }

        return fenceCount;
    }

    private boolean hasLeftFence(char[][] plotGrid, int x, int y) {
        int leftY = y - 1;

        return x >= 0 && x < plotGrid.length && (leftY < 0 || plotGrid[x][leftY] == ' ');
    }

    private boolean hasRightFence(char[][] plotGrid, int x, int y) {
        int rightY = y + 1;

        return x >= 0 && x < plotGrid.length && (rightY >= plotGrid[0].length || plotGrid[x][rightY] == ' ');
    }

    private boolean hasTopFence(char[][] plotGrid, int x, int y) {
        int topX = x - 1;

        return y >= 0 && y < plotGrid[0].length && (topX < 0 || plotGrid[topX][y] == ' ');
    }

    private boolean hasBottomFence(char[][] plotGrid, int x, int y) {
        int bottomX = x + 1;

        return y >= 0 && y < plotGrid[0].length && (bottomX >= plotGrid.length || plotGrid[bottomX][y] == ' ');
    }

    private record Tuple(long left, long right) {
    }

    private record Plot(char letter, long plotSize, long fenceCount) {
        public long getCost() {
            return plotSize * fenceCount;
        }
    }

}