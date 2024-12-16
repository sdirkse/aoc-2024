package com.github.sdirkse;

import com.github.sdirkse.utils.GridUtils;

import java.util.ArrayList;
import java.util.List;

class Day14 {

    long partOne(String input, int width, int height) {
        String[] lines = input.split("\r?\n");
        int[][] grid = GridUtils.createIntGrid(height, width, 0);

        List<Robot> robotList = getRobots(lines);

        initializeGrid(robotList, grid);

        for (int i = 0; i < 100; i++) {
            doTick(width, height, robotList, grid);
        }

        int halfWidth = (width - 1) / 2;
        int halfHeight = (height - 1) / 2;

        long quadrant1 = countRobots(grid, 0, halfHeight, 0, halfWidth);
        long quadrant2 = countRobots(grid, halfHeight + 1, height, 0, halfWidth);
        long quadrant3 = countRobots(grid, 0, halfHeight, halfWidth + 1, width);
        long quadrant4 = countRobots(grid, halfHeight + 1, height, halfWidth + 1, width);

        return quadrant1 * quadrant2 * quadrant3 * quadrant4;
    }

    private void initializeGrid(List<Robot> robotList, int[][] grid) {
        for (Robot robot : robotList) {
            grid[robot.getPosX()][robot.getPosY()] = grid[robot.getPosX()][robot.getPosY()] + 1;
        }
    }

    private void doTick(int width, int height, List<Robot> robotList, int[][] grid) {
        for (Robot robot : robotList) {
            int newHeight = robot.getPosX() + (robot.getVelocityX() % height);
            int newWidth = robot.getPosY() + (robot.getVelocityY() % width);

            if (newHeight < 0) {
                newHeight += height;
            }
            if (newHeight >= height) {
                newHeight -= height;
            }
            if (newWidth < 0) {
                newWidth += width;
            }
            if (newWidth >= width) {
                newWidth -= width;
            }

            grid[robot.getPosX()][robot.getPosY()] = grid[robot.getPosX()][robot.getPosY()] - 1;
            grid[newHeight][newWidth] = grid[newHeight][newWidth] + 1;

            robot.setPosX(newHeight);
            robot.setPosY(newWidth);
        }
    }

    private List<Robot> getRobots(String[] lines) {
        List<Robot> robotList = new ArrayList<>();

        for (String line : lines) {
            String[] splitLine = line.split(" ");
            String[] position = splitLine[0].substring(2).split(",");
            String[] velocity = splitLine[1].substring(2).split(",");

            Robot robot = new Robot(
                    Integer.parseInt(position[1]),
                    Integer.parseInt(position[0]),
                    Integer.parseInt(velocity[1]),
                    Integer.parseInt(velocity[0])
            );
            robotList.add(robot);
        }
        return robotList;
    }

    private long countRobots(int[][] grid, int startHeight, int endHeight, int startWidth, int endWidth) {
        long robotCount = 0;

        for (int x = startHeight; x < endHeight; x++) {
            for (int y = startWidth; y < endWidth; y++) {
                robotCount += grid[x][y];
            }
        }

        return robotCount;
    }

    long partTwo(String input, int width, int height) {
        String[] lines = input.split("\r?\n");
        int[][] grid = GridUtils.createIntGrid(height, width, 0);

        List<Robot> robotList = getRobots(lines);

        initializeGrid(robotList, grid);

        int halfWidth = (width - 1) / 2;
        int halfHeight = (height - 1) / 2;

        long lowestSafetyFactor = 1_000_000_000_000L;
        for (int second = 1; second < 10000; second++) {
            doTick(width, height, robotList, grid);

            long quadrant1 = countRobots(grid, 0, halfHeight, 0, halfWidth);
            long quadrant2 = countRobots(grid, halfHeight + 1, height, 0, halfWidth);
            long quadrant3 = countRobots(grid, 0, halfHeight, halfWidth + 1, width);
            long quadrant4 = countRobots(grid, halfHeight + 1, height, halfWidth + 1, width);

            long safetyFactor = quadrant1 * quadrant2 * quadrant3 * quadrant4;

            if (safetyFactor < lowestSafetyFactor) {
                System.out.printf("Lowest safety factor for second '%d' = '%d'", second, safetyFactor);
                System.out.println();
                lowestSafetyFactor = safetyFactor;
            }
        }

        return lowestSafetyFactor;
    }

    private class Robot {
        private int posX;
        private int posY;
        private int velocityX;
        private int velocityY;

        public Robot(int posX, int posY, int velocityX, int velocityY) {
            this.posX = posX;
            this.posY = posY;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
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

        public int getVelocityX() {
            return velocityX;
        }

        public void setVelocityX(int velocityX) {
            this.velocityX = velocityX;
        }

        public int getVelocityY() {
            return velocityY;
        }

        public void setVelocityY(int velocityY) {
            this.velocityY = velocityY;
        }
    }

}