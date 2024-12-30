package com.github.sdirkse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Day22 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");

        long sum = 0;
        for (String line : lines) {
            int number = Integer.parseInt(line);

            sum += getSecretNumberAfterIterations(number, 2000);
        }

        return sum;
    }

    private long getSecretNumberAfterIterations(long number, int iterations) {
        long secretNumber = number;

        for (int i = 1; i <= iterations; i++) {
            secretNumber = getSecretNumber(secretNumber);
        }

        return secretNumber;
    }

    private long getSecretNumber(long number) {
        number = ((number << 6) ^ number) & 0xFFFFFF;
        number = ((number >> 5) ^ number) & 0xFFFFFF;
        number = ((number << 11) ^ number) & 0xFFFFFF;

        return number;
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        Map<String, Long> priceChangeOccurangeMap = new HashMap<>();

        List<Long> priceChanges = new ArrayList<>();
        for (String line : lines) {
            Map<String, Long> currentPriceChangeOccuranceMap = new HashMap<>();
            long number = Long.parseLong(line);
            long lastNumber = number % 10;

            for (int i = 1; i <= 2000; i++) {
                number = getSecretNumber(number);

                long sellPrice = number % 10;
                priceChanges.add(sellPrice - lastNumber);

                lastNumber = sellPrice;

                if (priceChanges.size() == 4) {
                    String key = priceChanges.stream().map(String::valueOf).collect(Collectors.joining(","));
                    if (!currentPriceChangeOccuranceMap.containsKey(key)) {
                        currentPriceChangeOccuranceMap.put(key, sellPrice);
                    }

                    priceChanges.removeFirst();
                }
            }

            for (Map.Entry<String, Long> entry : currentPriceChangeOccuranceMap.entrySet()) {
                if (!priceChangeOccurangeMap.containsKey(entry.getKey())) {
                    priceChangeOccurangeMap.put(entry.getKey(), entry.getValue());
                } else {
                    priceChangeOccurangeMap.put(entry.getKey(), priceChangeOccurangeMap.get(entry.getKey()) + entry.getValue());
                }
            }
        }
        long highestValue = priceChangeOccurangeMap.values().stream().max(Long::compare).get();

        return highestValue;
    }

}