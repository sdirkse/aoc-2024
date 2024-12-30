package com.github.sdirkse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Day21 {

    private final Map<Character, int[]> numericKeypad = new HashMap<>() {
        {
            this.put('7', new int[]{0, 0});
            this.put('8', new int[]{0, 1});
            this.put('9', new int[]{0, 2});
            this.put('4', new int[]{1, 0});
            this.put('5', new int[]{1, 1});
            this.put('6', new int[]{1, 2});
            this.put('1', new int[]{2, 0});
            this.put('2', new int[]{2, 1});
            this.put('3', new int[]{2, 2});
            this.put('X', new int[]{3, 0});
            this.put('0', new int[]{3, 1});
            this.put('A', new int[]{3, 2});
        }
    };
    private final Map<Character, int[]> directionalKeypad = new HashMap<>() {
        {
            this.put('X', new int[]{0, 0});
            this.put('^', new int[]{0, 1});
            this.put('A', new int[]{0, 2});
            this.put('<', new int[]{1, 0});
            this.put('v', new int[]{1, 1});
            this.put('>', new int[]{1, 2});
        }
    };
    private final Map<Character, int[]> directions = new HashMap<>() {
        {
            this.put('^', new int[]{-1, 0});
            this.put('v', new int[]{1, 0});
            this.put('<', new int[]{0, -1});
            this.put('>', new int[]{0, 1});
        }
    };

    private Map<String, Long> movementCache = new HashMap<>();

    long partOne(String input) {
        String[] lines = input.split("\r?\n");

        return calculateComplexity(lines, 2);
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");

        return calculateComplexity(lines, 25);
    }


    private long calculateComplexity(String[] lines, long maxDepth) {
        long complexity = 0;
        for (String line : lines) {
            long numericPath = Long.parseLong(line.substring(0, 3));
            long minimumLength = getMinimumLength(line, maxDepth, 0);

            complexity += numericPath * minimumLength;
        }
        return complexity;
    }

    private long getMinimumLength(String line, long maxDepth, int depth) {
        String key = depth + "-" + maxDepth + "-" + line;

        if (movementCache.containsKey(key)) {
            return movementCache.get(key);
        }

        Map<Character, int[]> currentKeypad = depth == 0 ? numericKeypad : directionalKeypad;
        int[] currentPosition = currentKeypad.get('A');
        long currentLength = 0;

        for (char currentCharacter : line.toCharArray()) {
            int[] nextPosition = currentKeypad.get(currentCharacter);

            List<String> movementPermutations = getMovementPermutations(currentPosition, nextPosition, currentKeypad);

            if (depth == maxDepth) {
                currentLength += movementPermutations.getFirst().length();
            } else {
                currentLength += movementPermutations
                        .stream()
                        .mapToLong(perm -> getMinimumLength(perm, maxDepth, depth + 1))
                        .min()
                        .getAsLong();
            }

            currentPosition = nextPosition;
        }

        movementCache.put(key, currentLength);

        return currentLength;
    }

    private List<String> getMovementPermutations(int[] currentPosition, int[] nextPosition, Map<Character, int[]> keypad) {
        int diffX = nextPosition[0] - currentPosition[0];
        int diffY = nextPosition[1] - currentPosition[1];
        StringBuilder movements = new StringBuilder();

        int absoluteDiffX = Math.abs(diffX);
        if (diffX < 0) {
            movements.append("^".repeat(absoluteDiffX));
        } else {
            movements.append("v".repeat(absoluteDiffX));
        }

        int absoluteDiffY = Math.abs(diffY);
        if (diffY < 0) {
            movements.append("<".repeat(absoluteDiffY));
        } else {
            movements.append(">".repeat(absoluteDiffY));
        }

        int[] invalidPosition = keypad.get('X');
        List<String> possibleMovements = new ArrayList<>();
        Set<String> permutations = new HashSet<>();
        generatePermutations(movements.toString(), "", movements.length(), permutations);

        for (String permutation : permutations) {
            boolean hasPossibleMovement = true;
            int[] position = new int[]{currentPosition[0], currentPosition[1]};

            for (int i = 0; i < permutation.length(); i++) {
                int[] movement = directions.get(permutation.charAt(i));
                position[0] += movement[0];
                position[1] += movement[1];

                if (position[0] == invalidPosition[0] && position[1] == invalidPosition[1]) {
                    hasPossibleMovement = false;
                    break;
                }
            }

            if (hasPossibleMovement) {
                possibleMovements.add(permutation + "A");
            }
        }

        if (possibleMovements.isEmpty()) {
            possibleMovements.add("A");
        }

        return possibleMovements;
    }

    private void generatePermutations(String input, String permutation, int length, Set<String> permutationSet) {
        if (permutation.length() == length) {
            permutationSet.add(permutation);
            return;
        }

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            String remaining = input.substring(0, i) + input.substring(i + 1);
            generatePermutations(remaining, permutation + currentChar, length, permutationSet);
        }
    }
}