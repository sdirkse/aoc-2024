package com.github.sdirkse;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Day5 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        Map<String, Boolean> orderingRules = new HashMap<>();
        List<List<Integer>> updates = new ArrayList<>();

        boolean setOrderingRules = true;
        for (String line : lines) {
            if (line.isEmpty()) {
                setOrderingRules = false;
                continue;
            }

            if (setOrderingRules) {
                orderingRules.put(line, true);
            } else {
                updates.add(parseUpdate(line));
            }
        }

        long result = 0;
        for (List<Integer> update : updates) {
            if (!isValidUpdate(update, orderingRules)) {
                continue;
            }

            result += update.get(update.size() / 2);
        }

        return result;
    }

    private boolean isValidUpdate(List<Integer> update, Map<String, Boolean> orderingRules) {
        boolean isValidUpdate = true;

        for (int i = 0; i < update.size() && isValidUpdate; i++) {
            int currentNumber = update.get(i);
            List<Integer> numbersBefore = update.subList(0, i);
            List<Integer> numbersAfter = update.subList(i + 1, update.size());

            for (int numberBefore : numbersBefore) {
                if (orderingRules.getOrDefault(currentNumber + "|" + numberBefore, false)) {
                    isValidUpdate = false;
                }
            }
            for (int numberAfter : numbersAfter) {
                if (orderingRules.getOrDefault(numberAfter + "|" + currentNumber, false)) {
                    isValidUpdate = false;
                }
            }
        }
        return isValidUpdate;
    }

    private List<Integer> parseUpdate(String line) {
        return Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        Map<String, Boolean> orderingRules = new HashMap<>();
        List<List<Integer>> updates = new ArrayList<>();

        boolean setOrderingRules = true;
        for (String line : lines) {
            if (line.isEmpty()) {
                setOrderingRules = false;
                continue;
            }

            if (setOrderingRules) {
                orderingRules.put(line, true);
            } else {
                updates.add(parseUpdate(line));
            }
        }

        long result = 0;
        for (List<Integer> update : updates) {
            if (isValidUpdate(update, orderingRules)) {
                continue;
            }

            List<Integer> correctlySortedIncorrectUpdate = new ArrayList<>(update);
            int changesMadeToUpdate;
            do {
                changesMadeToUpdate = 0;

                for (int i = 1; i < update.size(); i++) {
                    int currentNumber = correctlySortedIncorrectUpdate.get(i);
                    List<Integer> numbersBefore = correctlySortedIncorrectUpdate.subList(0, i);

                    for (int numberBefore : numbersBefore) {
                        if (orderingRules.getOrDefault(currentNumber + "|" + numberBefore, false)) {
                            correctlySortedIncorrectUpdate.remove((Integer) currentNumber);
                            correctlySortedIncorrectUpdate.add(correctlySortedIncorrectUpdate.indexOf(numberBefore), currentNumber);
                            changesMadeToUpdate++;
                            break;
                        }
                    }
                }

                if (changesMadeToUpdate == 0) {
                    result += correctlySortedIncorrectUpdate.get(correctlySortedIncorrectUpdate.size() / 2);
                }
            } while (changesMadeToUpdate != 0);
        }

        return result;
    }

}