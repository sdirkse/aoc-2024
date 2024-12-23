package com.github.sdirkse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Day19 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        List<String> towelPatterns = new ArrayList<>(Arrays.stream(lines[0].split(", ")).toList());
        List<String> desiredPatterns = new ArrayList<>();

        for (int i = 2; i < lines.length; i++) {
            desiredPatterns.add(lines[i]);
        }

        towelPatterns.sort((a, b) -> Integer.compare(b.length(), a.length()));

        desiredPatterns = quickEliminate(towelPatterns, desiredPatterns);

        long matches = 0;
        for (String desiredPattern : desiredPatterns) {
            matches += checkPossibleCombinations(desiredPattern, towelPatterns);
        }

        return matches;
    }

    private List<String> quickEliminate(List<String> towelPatterns, List<String> desiredPatterns) {
        List<String> validPatterns = new ArrayList<>();

        for (String desiredPattern : desiredPatterns) {
            long count = towelPatterns.stream().filter(desiredPattern::endsWith).mapToInt(a -> 1).count();
            if (count > 0) {
                validPatterns.add(desiredPattern);
            }
        }

        return validPatterns;
    }

    private long checkPossibleCombinations(String desiredPattern, List<String> towelPatterns) {
        if (desiredPattern.isEmpty()) {
            return 1;
        }

        long count = 0;

        for (String towelPattern : towelPatterns) {
            if (desiredPattern.endsWith(towelPattern)) {
                count = checkPossibleCombinations(
                        desiredPattern.substring(0, desiredPattern.length() - towelPattern.length()),
                        towelPatterns
                );

                if (count == 1) {
                    return count;
                }
            }
        }

        return count;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        List<String> towelPatterns = new ArrayList<>(Arrays.stream(lines[0].split(", ")).toList());
        List<String> desiredPatterns = new ArrayList<>();

        for (int i = 2; i < lines.length; i++) {
            desiredPatterns.add(lines[i]);
        }

        long matches = 0;
        for (String desiredPattern : desiredPatterns) {
            matches += countPossibleCombinations(desiredPattern, towelPatterns);
        }

        return matches;
    }

    private long countPossibleCombinations(String desiredPattern, List<String> towelPatterns) {
        int n = desiredPattern.length();

        long[] dp = new long[n + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            if (dp[i] > 0) {
                for (String sequence : towelPatterns) {
                    int len = sequence.length();
                    if (i + len <= n && desiredPattern.substring(i, i + len).equals(sequence)) {
                        dp[i + len] += dp[i];
                    }
                }
            }
        }

        return dp[n];
    }

}