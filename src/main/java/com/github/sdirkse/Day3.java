package com.github.sdirkse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Day3 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");

        long result = 0;
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                result += calculateMultiplication(matcher.group());
            }
        }

        return result;
    }

    private long calculateMultiplication(String multiplication) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(multiplication);

        List<Long> numberList = new ArrayList<>();
        while (matcher.find()) {
            numberList.add(Long.parseLong(matcher.group()));
        }

        return numberList.getFirst() * numberList.getLast();
    }

    private boolean multiplicationEnabled = true;

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        Pattern pattern = Pattern.compile("^mul\\(\\d+,\\d+\\)");

        long result = 0;

        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                String currentLine = line.substring(i);

                if (currentLine.length() < 4) {
                    // No use in handling parts that can have no matches.
                    break;
                }

                Matcher matcher = pattern.matcher(currentLine);

                if (matcher.find()) {
                    if (multiplicationEnabled) {
                        result += calculateMultiplication(matcher.group());
                    }
                } else if (currentLine.startsWith("don't()")) {
                    multiplicationEnabled = false;
                } else if (currentLine.startsWith("do()")) {
                    multiplicationEnabled = true;
                }
            }
        }

        return result;
    }

}