package com.github.sdirkse;

import java.util.ArrayList;
import java.util.List;

class Day1 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        List<Long> leftLocationIds = new ArrayList<>();
        List<Long> rightLocationIds = new ArrayList<>();

        for (String line : lines) {
            String[] locationIds = line.split("\\s+");

            leftLocationIds.add(Long.parseLong(locationIds[0]));
            rightLocationIds.add(Long.parseLong(locationIds[1]));
        }

        leftLocationIds.sort(null);
        rightLocationIds.sort(null);

        long totalDistance = 0;
        for (int i = 0; i < leftLocationIds.size(); i++) {
            if (rightLocationIds.get(i) > leftLocationIds.get(i)) {
                totalDistance += rightLocationIds.get(i) - leftLocationIds.get(i);
            } else {
                totalDistance += leftLocationIds.get(i) - rightLocationIds.get(i);
            }
        }

        return totalDistance;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        List<Long> leftLocationIds = new ArrayList<>();
        List<Long> rightLocationIds = new ArrayList<>();

        for (String line : lines) {
            String[] locationIds = line.split("\\s+");

            leftLocationIds.add(Long.parseLong(locationIds[0]));
            rightLocationIds.add(Long.parseLong(locationIds[1]));
        }

        long similarityScore = 0;

        for (long currentLocationId : leftLocationIds) {
            long currentMatchCount = rightLocationIds.stream().filter(n -> n.equals(currentLocationId)).count();

            similarityScore += currentLocationId * currentMatchCount;
        }

        return similarityScore;
    }

}