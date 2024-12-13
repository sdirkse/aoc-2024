package com.github.sdirkse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Day11 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");
        List<Long> numberList = Arrays.stream(lines[0].split("\\s")).map(Long::parseLong).collect(Collectors.toList());

        for (int blink = 1; blink <= 25; blink++) {
            numberList = doBlink(numberList);

//            System.out.printf("After blinking '%d' time, you have '%d' stones", blink, numberList.size());
//            System.out.println();
//            System.out.println(numberList);
        }

        return numberList.size();
    }

    private List<Long> doBlink(List<Long> numberList) {
        List<Long> newNumberList = new ArrayList<>();

        for (long number : numberList) {
            handleNumber(number, newNumberList);
        }

        return newNumberList;
    }

    private void handleNumber(long number, List<Long> numberList) {
        if (number == 0) {
            numberList.add(number + 1);
        } else if (String.valueOf(number).length() % 2 == 0) {
            String numberString = String.valueOf(number);
            numberList.add(Long.valueOf(numberString.substring(0, numberString.length() / 2)));
            numberList.add(Long.valueOf(numberString.substring((numberString.length() / 2))));
        } else {
            numberList.add(number * 2024);
        }
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        List<Long> numberList = Arrays.stream(lines[0].split("\\s")).map(Long::parseLong).collect(Collectors.toList());
        Map<Long, Long> numberOccuranceMap = new HashMap<>();

        long stoneCount = 0;
        for (Long number : numberList) {
            numberOccuranceMap.put(number, 1L);
        }

        Map<Long, Long> resultMap = recursiveBlink(numberOccuranceMap, 1);

        for (Map.Entry<Long, Long> mapEntry : resultMap.entrySet()) {
            stoneCount += mapEntry.getValue();
        }

        return stoneCount;
    }

    private Map<Long, Long> recursiveBlink(Map<Long, Long> numberOccuranceMap, int currentBlink) {
        if (currentBlink > 75) {
            return numberOccuranceMap;
        }

        Map<Long, Long> newNumberOccuranceMap = new HashMap<>();

        for (Map.Entry<Long, Long> mapEntry : numberOccuranceMap.entrySet()) {
            List<Long> results = new ArrayList<>();
            handleNumber(mapEntry.getKey(), results);

            for (Long result : results) {
                newNumberOccuranceMap.compute(result, (k, v) -> (v == null) ? mapEntry.getValue() : v + mapEntry.getValue());
            }
        }

        return recursiveBlink(newNumberOccuranceMap, currentBlink + 1);
    }

}