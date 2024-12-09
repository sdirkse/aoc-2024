package com.github.sdirkse;

import java.util.HashMap;
import java.util.Map;

class Day6 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = createGrid(lines);
        char[][] visited = new char[lines.length][lines[0].length()];

        Coordinates guardCoordinates = findGuardStartingCoordinates(grid);

        determineVisitedFields(visited, guardCoordinates, grid);

        int visitedCount = 0;
        for (char[] chars : visited) {
            for (char singleChar : chars) {
                if (singleChar == 'X') {
                    visitedCount++;
                }
            }
        }

        return visitedCount;
    }

    private void determineVisitedFields(char[][] visited, Coordinates guardCoordinates, char[][] grid) {
        visited[guardCoordinates.getX()][guardCoordinates.getY()] = 'X';
        char direction = grid[guardCoordinates.getX()][guardCoordinates.getY()];

        do {
            direction = walkGuard(grid, guardCoordinates, direction);

            if (direction == 'X') {
                return;
            }

            visited[guardCoordinates.getX()][guardCoordinates.getY()] = 'X';
        } while (true);
    }

    private char walkGuard(char[][] grid, Coordinates guardCoordinates, char direction) {
        if (direction == '^') {
            if (guardCoordinates.getX() - 1 < 0) {
                guardCoordinates.setX(guardCoordinates.getX() - 1);
                return 'X';
            }
            if (isSolidObject(grid[guardCoordinates.getX() - 1][guardCoordinates.getY()])) {
                direction = walkGuard(grid, guardCoordinates, '>');
            } else {
                guardCoordinates.setX(guardCoordinates.getX() - 1);
            }
        } else if (direction == 'v') {
            if (guardCoordinates.getX() + 1 == grid.length) {
                guardCoordinates.setX(guardCoordinates.getX() + 1);
                return 'X';
            }
            if (isSolidObject(grid[guardCoordinates.getX() + 1][guardCoordinates.getY()])) {
                direction = walkGuard(grid, guardCoordinates, '<');
            } else {
                guardCoordinates.setX(guardCoordinates.getX() + 1);
            }
        } else if (direction == '<') {
            if (guardCoordinates.getY() - 1 < 0) {
                guardCoordinates.setY(guardCoordinates.getY() - 1);
                return 'X';
            }
            if (isSolidObject(grid[guardCoordinates.getX()][guardCoordinates.getY() - 1])) {
                direction = walkGuard(grid, guardCoordinates, '^');
            } else {
                guardCoordinates.setY(guardCoordinates.getY() - 1);
            }
        } else if (direction == '>') {
            if (guardCoordinates.getY() + 1 == grid[0].length) {
                guardCoordinates.setY(guardCoordinates.getY() + 1);
                return 'X';
            }
            if (isSolidObject(grid[guardCoordinates.getX()][guardCoordinates.getY() + 1])) {
                direction = walkGuard(grid, guardCoordinates, 'v');
            } else {
                guardCoordinates.setY(guardCoordinates.getY() + 1);
            }
        }

        return direction;
    }

    private boolean isSolidObject(char field) {
        return field == '#';
    }

    private char[][] createGrid(String[] lines) {
        char[][] grid = new char[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        return grid;
    }

    private Coordinates findGuardStartingCoordinates(char[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == '^') {
                    return new Coordinates(x, y);
                }
            }
        }

        throw new RuntimeException("No guard could be found");
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = createGrid(lines);
        char[][] visited = new char[lines.length][lines[0].length()];

        Coordinates guardCoordinates = findGuardStartingCoordinates(grid);

        determineVisitedFields(visited, new Coordinates(guardCoordinates.getX(), guardCoordinates.getY()), grid);

        int visitedCount = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (x == guardCoordinates.getX() && y == guardCoordinates.getY()) {
                    continue;
                }

                if (visited[x][y] == 'X') {
                    if (checkLoop(grid, new Coordinates(x, y), new Coordinates(guardCoordinates.getX(), guardCoordinates.getY()))) {
                        visitedCount++;
                    }
                }
            }
        }

        return visitedCount;
    }

    private char[][] cloneGrid(char[][] grid) {
        char[][] newGrid = new char[grid.length][grid[0].length];

        for (int row = 0; row < grid.length; row++) {
            System.arraycopy(grid[row], 0, newGrid[row], 0, grid[row].length);
        }

        return newGrid;
    }

    private boolean checkLoop(char[][] grid, Coordinates obstacle, Coordinates coordinates) {
        Map<String, Character> directionPositionVisited = new HashMap<>();

        char[][] newGrid = cloneGrid(grid);
        newGrid[obstacle.getX()][obstacle.getY()] = '#';

        char direction = newGrid[coordinates.getX()][coordinates.getY()];
        directionPositionVisited.put(coordinates.getX() + "," + coordinates.getY(), direction);

        do {
            direction = walkGuard(newGrid, coordinates, direction);

            String directionPositionKey = coordinates.getX() + "," + coordinates.getY();

            if (directionPositionVisited.containsKey(directionPositionKey)
                    && directionPositionVisited.get(directionPositionKey) == direction) {
                return true;
            }

            if (direction == 'X') {
                return false;
            }
            newGrid[coordinates.getX()][coordinates.getY()] = 'X';
            directionPositionVisited.put(directionPositionKey, direction);
        } while (true);
    }

    private class Coordinates {
        private int x = 0;
        private int y = 0;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }


    }

}