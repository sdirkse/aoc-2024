package com.github.sdirkse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Day13 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        Pattern numberPattern = Pattern.compile("(\\d+).*?(\\d+)");
        List<ClawMachine> clawMachineList = new ArrayList<>();

        Button buttonA = null;
        Button buttonB = null;
        Prize prize = null;

        for (int i = 0; i < lines.length; i += 3) {
            if (lines[i].isEmpty()) {
                i++;
            }

            Matcher buttonAMatcher = numberPattern.matcher(lines[i]);
            while (buttonAMatcher.find()) {
                buttonA = new Button(Integer.parseInt(buttonAMatcher.group(1)), Integer.parseInt(buttonAMatcher.group(2)));
            }

            Matcher buttonBMatcher = numberPattern.matcher(lines[i + 1]);
            while (buttonBMatcher.find()) {
                buttonB = new Button(Integer.parseInt(buttonBMatcher.group(1)), Integer.parseInt(buttonBMatcher.group(2)));
            }

            Matcher prizeMatcher = numberPattern.matcher(lines[i + 2]);
            while (prizeMatcher.find()) {
                prize = new Prize(Integer.parseInt(prizeMatcher.group(1)), Integer.parseInt(prizeMatcher.group(2)));
            }

            clawMachineList.add(new ClawMachine(buttonA, buttonB, prize));
        }

        long tokenCount = 0;
        for (ClawMachine clawMachine : clawMachineList) {
            tokenCount += calculateTokensForPrize(clawMachine);
        }

        return tokenCount;
    }

    private long calculateTokensForPrize(ClawMachine clawMachine) {
        long x = clawMachine.prize.x;
        long y = clawMachine.prize.y;
        long a1 = clawMachine.buttonA.incrementX;
        long a2 = clawMachine.buttonA.incrementY;
        long b1 = clawMachine.buttonB.incrementX;
        long b2 = clawMachine.buttonB.incrementY;

        long bNumerator = y * a1 - a2 * x;
        long bDenominator = -a2 * b1 + a1 * b2;

        long b = bNumerator / bDenominator;
        long bMod = bNumerator % bDenominator;

        long aNumerator = x - b * b1;
        long a = aNumerator / a1;
        long aMod = aNumerator % a1;

        if (a == 0 || b == 0 || aMod != 0 || bMod != 0) {
            return 0;
        }

        return a * 3 + b;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        Pattern numberPattern = Pattern.compile("(\\d+).*?(\\d+)");
        List<ClawMachine> clawMachineList = new ArrayList<>();

        Button buttonA = null;
        Button buttonB = null;
        Prize prize = null;

        for (int i = 0; i < lines.length; i += 3) {
            if (lines[i].isEmpty()) {
                i++;
            }

            Matcher buttonAMatcher = numberPattern.matcher(lines[i]);
            while (buttonAMatcher.find()) {
                buttonA = new Button(Integer.parseInt(buttonAMatcher.group(1)), Integer.parseInt(buttonAMatcher.group(2)));
            }

            Matcher buttonBMatcher = numberPattern.matcher(lines[i + 1]);
            while (buttonBMatcher.find()) {
                buttonB = new Button(Integer.parseInt(buttonBMatcher.group(1)), Integer.parseInt(buttonBMatcher.group(2)));
            }

            Matcher prizeMatcher = numberPattern.matcher(lines[i + 2]);
            while (prizeMatcher.find()) {
                prize = new Prize(Long.parseLong(prizeMatcher.group(1)) + 10_000_000_000_000L, Long.parseLong(prizeMatcher.group(2)) + 10_000_000_000_000L);
            }

            clawMachineList.add(new ClawMachine(buttonA, buttonB, prize));
        }

        long tokenCount = 0;
        for (ClawMachine clawMachine : clawMachineList) {
            tokenCount += calculateTokensForPrize(clawMachine);
        }

        return tokenCount;
    }

    public record ClawMachine(Button buttonA, Button buttonB, Prize prize) {
    }

    public record Button(int incrementX, int incrementY) {
    }

    public record Prize(long x, long y) {
    }

}