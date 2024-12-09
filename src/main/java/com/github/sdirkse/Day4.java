package com.github.sdirkse;

class Day4 {

    private final int[][] xmasDirections = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    long partOne(String input) {
        String[] lines = input.split("\n");
        char[][] grid = new char[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        return countXMASWords(grid);
    }

    private long countXMASWords(char[][] grid) {
        long wordCount = 0;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (!String.valueOf(grid[x][y]).equals("X")) {
                    continue;
                }
                for (int[] direction : xmasDirections) {
                    if (hasWordXmas(grid, x, y, direction, "")) {
                        wordCount++;
                    }
                }
            }
        }

        return wordCount;
    }

    private boolean hasWordXmas(char[][] grid, int x, int y, int[] direction, String word) {
        if (word.equals("XMAS")) {
            return true;
        }
        if (x < 0 || x >= grid.length
                || y < 0 || y >= grid[x].length) {
            return false;
        }

        word += String.valueOf(grid[x][y]);

        if (!word.isEmpty() && isWordNotLikeXmas(word)) {
            return false;
        }

        return hasWordXmas(grid, x + direction[0], y + direction[1], direction, word);
    }

    private boolean isWordNotLikeXmas(String word) {
        if (word.isEmpty()) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != "XMAS".charAt(i)) {
                return true;
            }
        }

        return false;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        char[][] grid = new char[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                grid[i][j] = lines[i].charAt(j);
            }
        }

        return countCrossMASWords(grid);
    }

    private long countCrossMASWords(char[][] grid) {
        long wordCount = 0;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (!String.valueOf(grid[x][y]).equals("A")) {
                    continue;
                }
                if ((x == 0 || x == grid.length - 1) || (y == 0 || y == grid[x].length - 1)) {
                    continue;
                }
                String firstWord = String.valueOf(grid[x - 1][y - 1]) + grid[x][y] + grid[x + 1][y + 1];
                String secondWord = String.valueOf(grid[x + 1][y - 1]) + grid[x][y] + grid[x - 1][y + 1];

                if ((firstWord.equals("MAS") && secondWord.equals("MAS"))
                        || (firstWord.equals("MAS") && secondWord.equals("SAM"))
                        || (firstWord.equals("SAM") && secondWord.equals("MAS"))
                        || (firstWord.equals("SAM") && secondWord.equals("SAM"))) {
                    wordCount++;
                }
            }
        }

        return wordCount;
    }

}