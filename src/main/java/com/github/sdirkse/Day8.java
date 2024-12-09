package com.github.sdirkse;

import com.github.sdirkse.utils.GridUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Day8 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = createGrid(lines);
        char[][] poleGrid = GridUtils.createGrid(lines.length, lines[0].length(), '.');
        Map<Character, List<Coordinates>> characterCoordinates = new HashMap<>();

        for (int x = 0; x < lines.length; x++) {
            for (int y = 0; y < lines[x].length(); y++) {
                if (grid[x][y] != '.') {
                    char gridCharacter = grid[x][y];
                    if (characterCoordinates.containsKey(gridCharacter)) {
                        characterCoordinates.get(gridCharacter).add(new Coordinates(x, y));
                    } else {
                        characterCoordinates.put(gridCharacter, new ArrayList<>(List.of(new Coordinates(x, y))));
                    }
                }
            }
        }

        for (Map.Entry<Character, List<Coordinates>> entry : characterCoordinates.entrySet()) {
            List<Coordinates> coordinatesList = entry.getValue();
            for (int i = 0; i < coordinatesList.size(); i++) {
                for (int j = i + 1; j < coordinatesList.size(); j++) {
                    int coordX1 = coordinatesList.get(i).x;
                    int coordY1 = coordinatesList.get(i).y;
                    int coordX2 = coordinatesList.get(j).x;
                    int coordY2 = coordinatesList.get(j).y;

                    int diffX = coordX1 - coordX2;
                    int diffY = coordY1 - coordY2;

                    addToPoleGrid(poleGrid, coordX1 + diffX, coordY1 + diffY);
                    addToPoleGrid(poleGrid, coordX2 - diffX, coordY2 - diffY);
                }
            }
        }

        long polesWithinGrid = 0;
        for (char[] chars : poleGrid) {
            for (char singleChar : chars) {
                if (singleChar == '#') {
                    polesWithinGrid++;
                }
            }
        }

        return polesWithinGrid;
    }

    private void addToPoleGrid(char[][] poleGrid, int x, int y) {
        if (x < 0 || x >= poleGrid.length) {
            return;
        }
        if (y < 0 || y >= poleGrid[0].length) {
            return;
        }
        poleGrid[x][y] = '#';
    }

    private boolean recursiveAddToPoleGrid(char[][] poleGrid, int x, int addX, int y, int addY) {
        int newX = x + addX;
        int newY = y + addY;

        if (newX < 0 || newX >= poleGrid.length) {
            return false;
        }
        if (newY < 0 || newY >= poleGrid[0].length) {
            return false;
        }
        poleGrid[newX][newY] = '#';
        return recursiveAddToPoleGrid(poleGrid, newX, addX, newY, addY);
    }

    private char[][] createGrid(String[] lines) {
        char[][] grid = GridUtils.createGrid(lines.length, lines[0].length());

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        return grid;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = createGrid(lines);
        char[][] poleGrid = GridUtils.createGrid(lines.length, lines[0].length(), '.');
        Map<Character, List<Coordinates>> characterCoordinates = new HashMap<>();

        for (int x = 0; x < lines.length; x++) {
            for (int y = 0; y < lines[x].length(); y++) {
                if (grid[x][y] != '.') {
                    char gridCharacter = grid[x][y];
                    if (characterCoordinates.containsKey(gridCharacter)) {
                        characterCoordinates.get(gridCharacter).add(new Coordinates(x, y));
                    } else {
                        characterCoordinates.put(gridCharacter, new ArrayList<>(List.of(new Coordinates(x, y))));
                    }
                }
            }
        }

        for (Map.Entry<Character, List<Coordinates>> entry : characterCoordinates.entrySet()) {
            List<Coordinates> coordinatesList = entry.getValue();
            for (int i = 0; i < coordinatesList.size(); i++) {
                for (int j = i + 1; j < coordinatesList.size(); j++) {
                    int coordX1 = coordinatesList.get(i).x;
                    int coordY1 = coordinatesList.get(i).y;
                    int coordX2 = coordinatesList.get(j).x;
                    int coordY2 = coordinatesList.get(j).y;

                    int diffX = coordX1 - coordX2;
                    int diffY = coordY1 - coordY2;

                    recursiveAddToPoleGrid(poleGrid, coordX1, diffX, coordY1, diffY);
                    recursiveAddToPoleGrid(poleGrid, coordX2, -diffX, coordY2, -diffY);
                }
            }
        }

        for (int x = 0; x < poleGrid.length; x++) {
            for (int y = 0; y < poleGrid[x].length; y++) {
                if (poleGrid[x][y] == '#') {
                    grid[x][y] = poleGrid[x][y];
                }
            }
        }
        long polesWithinGrid = 0;
        for (char[] row : grid) {
            for (char singleChar : row) {
                if (singleChar != '.') {
                    polesWithinGrid++;
                }
            }
        }

        return polesWithinGrid;
    }

    private record Coordinates(int x, int y) {
    }

}