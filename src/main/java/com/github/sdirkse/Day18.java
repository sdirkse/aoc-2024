package com.github.sdirkse;

import com.github.sdirkse.utils.GridUtils;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class Day18 {

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    long partOne(String input, int height, int width) {
        String[] lines = input.split("\r?\n");
        char[][] grid = GridUtils.createGrid(height, width, '.');

        for (int failingByte = 0; failingByte < 1024; failingByte++) {
            int[] coords = Arrays.stream(lines[failingByte].split(",")).mapToInt(Integer::parseInt).toArray();
            grid[coords[1]][coords[0]] = '#';
        }

        long shortestPath = findShortestPath(grid, 0, 0, height - 1, width - 1);

        return shortestPath;
    }

    private long findShortestPath(char[][] grid, int startX, int startY, int targetX, int targetY) {
        Queue<Coordinate> queue = new PriorityQueue<>();
        char[][] visited = GridUtils.copyGrid(grid);

        queue.add(new Coordinate(startX, startY, 0));

        while (!queue.isEmpty()) {
            Coordinate currentCoord = queue.poll();

            if (currentCoord.x == targetX && currentCoord.y == targetY) {
                return currentCoord.steps;
            }

            visited[currentCoord.x][currentCoord.y] = 'O';

            for (int[] direction : directions) {
                int newX = currentCoord.x + direction[0];
                int newY = currentCoord.y + direction[1];

                if (isOutsideGrid(newX, newY, targetX, targetY)) {
                    continue;
                }
                Coordinate newCoordinate = new Coordinate(newX, newY, currentCoord.steps + 1);

                if (visited[newX][newY] == '.' && !queue.contains(newCoordinate)) {
                    queue.add(newCoordinate);
                }
            }
        }

        return -1;
    }

    private boolean isOutsideGrid(int x, int y, int height, int width) {
        return x < 0 || x > height || y < 0 || y > width;
    }

    long partTwo(String input, int height, int width) {
        String[] lines = input.split("\r?\n");
        char[][] grid = GridUtils.createGrid(height, width, '.');

        for (int failingByte = 0; failingByte < 1024; failingByte++) {
            int[] coords = Arrays.stream(lines[failingByte].split(",")).mapToInt(Integer::parseInt).toArray();
            grid[coords[1]][coords[0]] = '#';
        }

        long shortestPath = 0;
        int failingByte = 1024;
        do {
            int[] coords = Arrays.stream(lines[failingByte].split(",")).mapToInt(Integer::parseInt).toArray();
            grid[coords[1]][coords[0]] = '#';

            shortestPath = findShortestPath(grid, 0, 0, height - 1, width - 1);

            failingByte++;
        } while (shortestPath != -1);

        int[] failedCoords = Arrays.stream(lines[failingByte - 1].split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.printf("[%d, %d]", failedCoords[0], failedCoords[1]);

        return shortestPath;
    }

    private record Coordinate(int x, int y, int steps) implements Comparable<Coordinate> {
        @Override
        public int compareTo(Coordinate other) {
            return Integer.compare(this.steps, other.steps);
        }
    }

}