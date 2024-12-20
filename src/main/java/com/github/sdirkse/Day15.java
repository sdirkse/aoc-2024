package com.github.sdirkse;

import com.github.sdirkse.utils.GridUtils;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Day15 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = new char[lines[0].length()][lines[0].length()];
        List<Character> movements = new ArrayList<>();

        boolean parseGrid = true;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isEmpty()) {
                parseGrid = false;
                continue;
            }

            if (parseGrid) {
                grid[i] = lines[i].toCharArray();
            } else {
                for (Character movement : lines[i].toCharArray()) {
                    movements.add(movement);
                }
            }
        }

        Robot robot = null;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == '@') {
                    robot = new Robot(x, y);
                    break;
                }
            }
        }

        doMovements(grid, robot, movements);

        return calculateSumOfGPSCoordinates(grid);
    }

    private long calculateSumOfGPSCoordinates(char[][] grid) {
        long sum = 0;

        for (int x = 1; x < grid.length - 1; x++) {
            for (int y = 1; y < grid[0].length - 1; y++) {
                if (grid[x][y] == 'O') {
                    sum += (x * 100L) + y;
                }
            }
        }

        return sum;
    }

    private long calculateSumOfWideBoxGPSCoordinates(char[][] grid) {
        long sum = 0;
        int height = grid.length;
        int width = grid[0].length;

        int boxCount = 0;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (grid[x][y] == '[') {
                    sum += (x * 100L) + y;
                    boxCount++;
                }
            }
        }

        System.out.println("Amount of boxes in final solution: " + boxCount);
        System.out.println("Final score: " + sum);

        return sum;
    }

    private void doMovements(char[][] grid, Robot robot, List<Character> movements) {
//        int steps = 0;
//        createImage(grid, steps);

        for (char movement : movements) {
            char object = ' ';
            int newPosX = robot.getPosX();
            int newPosY = robot.getPosY();

            switch (movement) {
                case '^' -> {
                    newPosX--;
                    object = grid[newPosX][newPosY];
                }
                case '>' -> {
                    newPosY++;
                    object = grid[newPosX][newPosY];
                }
                case 'v' -> {
                    newPosX++;
                    object = grid[newPosX][newPosY];
                }
                case '<' -> {
                    newPosY--;
                    object = grid[newPosX][newPosY];
                }
            }

            boolean canMoveRobot = false;
            if (object == 'O') {
                canMoveRobot = moveBoxes(grid, robot.getPosX(), robot.getPosY(), movement);
            } else if (object == '[' || object == ']') {
                canMoveRobot = moveWideBoxes(grid, robot.getPosX(), robot.getPosY(), movement);
            }

            if (object == '.' || canMoveRobot) {
                grid[robot.getPosX()][robot.getPosY()] = '.';
                robot.setPosX(newPosX);
                robot.setPosY(newPosY);
                grid[robot.getPosX()][robot.getPosY()] = '@';
            }
//            steps++;
//            createImage(grid, steps);
        }
    }

    private void createImage(char[][] grid, int step) {
        int fontSize = 12;
        int rows = grid.length;
        int cols = grid[0].length;
        int height = rows * fontSize;
        int width = cols * fontSize;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.PLAIN, fontSize));

        int y = fontSize;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                g.drawString(String.valueOf(grid[row][col]), col * fontSize, y);
            }
            y += fontSize;
        }

        g.dispose();

        try {
            ImageIO.write(image, "png", new File("output/grid_image_" + step + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final int[] UP = {-1, 0};
    private final int[] DOWN = {1, 0};
    private final int[] LEFT = {0, -1};
    private final int[] RIGHT = {0, 1};

    private boolean moveBoxes(char[][] grid, int posX, int posY, char movement) {
        switch (movement) {
            case '^' -> {
                return swapPositions(grid, posX, posY, UP);
            }
            case '>' -> {
                return swapPositions(grid, posX, posY, RIGHT);
            }
            case 'v' -> {
                return swapPositions(grid, posX, posY, DOWN);
            }
            case '<' -> {
                return swapPositions(grid, posX, posY, LEFT);
            }
        }

        return false;
    }

    private boolean moveWideBoxes(char[][] grid, int posX, int posY, char movement) {
        switch (movement) {
            case '^' -> {
                return swapWidePositions(grid, posX, posY, UP);
            }
            case '>' -> {
                return swapWidePositions(grid, posX, posY, RIGHT);
            }
            case 'v' -> {
                return swapWidePositions(grid, posX, posY, DOWN);
            }
            case '<' -> {
                return swapWidePositions(grid, posX, posY, LEFT);
            }
        }

        return false;
    }

    private boolean swapPositions(char[][] grid, int posX, int posY, int[] direction) {
        char object = grid[posX + direction[0]][posY + direction[1]];

        if (object == '#') {
            return false;
        }

        boolean swapPosition = false;
        if (object == 'O') {
            swapPosition = swapPositions(grid, posX + direction[0], posY + direction[1], direction);
        }

        if (object == '.' || swapPosition) {
            grid[posX + direction[0]][posY + direction[1]] = grid[posX][posY];
            grid[posX][posY] = '.';
            swapPosition = true;
        }

        return swapPosition;
    }

    private boolean swapWidePositions(char[][] grid, int posX, int posY, int[] direction) {
        char object = grid[posX + direction[0]][posY + direction[1]];

        if (object == '#') {
            return false;
        }

        boolean swapPosition = false;
        if (object == '[') {
            if (direction == RIGHT || direction == LEFT) {
                swapPosition = swapWidePositions(grid, posX + direction[0], posY + direction[1], direction);
            }
            if (direction == DOWN || direction == UP) {
                boolean canMoveWideBoxes = true;
                canMoveWideBoxes &= canMoveWideBox(grid, posX + direction[0], posY, direction);
                canMoveWideBoxes &= canMoveWideBox(grid, posX + direction[0], posY + 1, direction);

                if (canMoveWideBoxes) {
                    swapPosition = moveWideBox(grid, posX + direction[0], posY, direction);
                }
            }
        } else if (object == ']') {
            if (direction == RIGHT || direction == LEFT) {
                swapPosition = swapWidePositions(grid, posX + direction[0], posY + direction[1], direction);
            }
            if (direction == DOWN || direction == UP) {
                boolean canMoveWideBoxes = true;
                canMoveWideBoxes &= canMoveWideBox(grid, posX + direction[0], posY, direction);
                canMoveWideBoxes &= canMoveWideBox(grid, posX + direction[0], posY - 1, direction);

                if (canMoveWideBoxes) {
                    swapPosition = moveWideBox(grid, posX + direction[0], posY, direction);
                }
            }
        }

        if (object == '.' || swapPosition) {
            grid[posX + direction[0]][posY + direction[1]] = grid[posX][posY];
            grid[posX][posY] = '.';
            swapPosition = true;
        }

        return swapPosition;
    }

    private boolean moveWideBox(char[][] grid, int posX, int posY, int[] direction) {
        char object = grid[posX][posY];

        if (object == '.') {
            return true;
        }

        if (object == '[') {
            if (moveWideBox(grid, posX + direction[0], posY, direction) && moveWideBox(grid, posX + direction[0], posY + 1, direction)) {
                grid[posX + direction[0]][posY] = grid[posX][posY];
                grid[posX][posY] = '.';
                grid[posX + direction[0]][posY + 1] = grid[posX][posY + 1];
                grid[posX][posY + 1] = '.';
                return true;
            }
        } else if (object == ']') {
            if (moveWideBox(grid, posX + direction[0], posY, direction) && moveWideBox(grid, posX + direction[0], posY - 1, direction)) {
                grid[posX + direction[0]][posY] = grid[posX][posY];
                grid[posX][posY] = '.';
                grid[posX + direction[0]][posY - 1] = grid[posX][posY - 1];
                grid[posX][posY - 1] = '.';
                return true;
            }
        }

        return false;
    }

    private boolean canMoveWideBox(char[][] grid, int posX, int posY, int[] direction) {
        char object = grid[posX + direction[0]][posY + direction[1]];

        if (object == '#') {
            return false;
        }
        if (object == '.') {
            return true;
        }

        boolean canMoveBox = true;

        if (object == '[') {
            canMoveBox &= canMoveWideBox(grid, posX + direction[0], posY, direction);
            canMoveBox &= canMoveWideBox(grid, posX + direction[0], posY + 1, direction);
        } else if (object == ']') {
            canMoveBox &= canMoveWideBox(grid, posX + direction[0], posY, direction);
            canMoveBox &= canMoveWideBox(grid, posX + direction[0], posY - 1, direction);
        }

        return canMoveBox;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = new char[lines[0].length()][lines[0].length() * 2];
        List<Character> movements = new ArrayList<>();

        boolean parseGrid = true;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isEmpty()) {
                parseGrid = false;
                continue;
            }

            if (parseGrid) {
                int nextPos = 0;
                for (int j = 0; j < lines[i].length(); j++) {
                    char currentChar = lines[i].charAt(j);
                    if (currentChar == '@') {
                        grid[i][nextPos++] = currentChar;
                        grid[i][nextPos++] = '.';
                    } else if (currentChar == 'O') {
                        grid[i][nextPos++] = '[';
                        grid[i][nextPos++] = ']';
                    } else {
                        grid[i][nextPos++] = currentChar;
                        grid[i][nextPos++] = currentChar;
                    }
                }
            } else {
                for (Character movement : lines[i].toCharArray()) {
                    movements.add(movement);
                }
            }
        }

        Robot robot = null;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == '@') {
                    robot = new Robot(x, y);
                    break;
                }
            }
        }

        doMovements(grid, robot, movements);

        GridUtils.drawGrid(grid);

        return calculateSumOfWideBoxGPSCoordinates(grid);
    }

    private class Robot {
        private int posX;
        private int posY;

        public Robot(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        public int getPosX() {
            return posX;
        }

        public void setPosX(int posX) {
            this.posX = posX;
        }

        public int getPosY() {
            return posY;
        }

        public void setPosY(int posY) {
            this.posY = posY;
        }
    }

}