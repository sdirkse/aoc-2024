package com.github.sdirkse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day2 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");

        long safeReportCount = 0;

        for (String line : lines) {
            List<Integer> numbers = Arrays.stream(line.split("\\s")).map(Integer::parseInt).collect(Collectors.toList());

            if (isSafeReport(numbers)) {
                safeReportCount++;
            }
        }

        return safeReportCount;
    }

    public boolean isSafeReport(List<Integer> reportNumbers) {
        List<Integer> numbers = new ArrayList<>(reportNumbers);
        int lastNumber = numbers.removeFirst();
        boolean isIncrement = (numbers.getFirst() - lastNumber) >= 0;

        for (int number : numbers) {
            int diff = number - lastNumber;

            if ((isIncrement && diff < 0) || (!isIncrement && diff > 0)) {
                return false;
            }

            if (!isIncrement) {
                diff *= -1;
            }

            if (diff < 1 || diff > 3) {
                return false;
            }

            lastNumber = number;
        }

        return true;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");

        long safeReportCount = 0;

        for (String line : lines) {
            List<Integer> numbers = Arrays.stream(line.split("\\s")).map(Integer::parseInt).collect(Collectors.toList());

            if (isSafeReport(numbers)) {
                safeReportCount++;
            } else {
                boolean hasSafeReportPossibility = false;

                for (int i = 0; !hasSafeReportPossibility && i < numbers.size(); i++) {
                    List<Integer> newNumbers = new ArrayList<>(numbers);
                    newNumbers.remove(i);

                    if (isSafeReport(newNumbers)) {
                        hasSafeReportPossibility = true;
                    }
                }

                if (hasSafeReportPossibility) {
                    safeReportCount++;
                }
            }
        }

        return safeReportCount;
    }

}