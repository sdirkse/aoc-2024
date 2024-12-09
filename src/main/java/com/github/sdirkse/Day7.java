package com.github.sdirkse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day7 {

    BigInteger partOne(String input) {
        String[] lines = input.split("\r?\n");

        char[] operators = {'+', '*'};

        BigInteger totalCalibrationResult = BigInteger.ZERO;
        for (String line : lines) {
            String[] resultNumberSplit = line.split(":\\s");
            BigInteger requiredResult = new BigInteger(resultNumberSplit[0]);
            List<Long> numbers = Arrays.stream(resultNumberSplit[1].split("\\s")).map(Long::parseLong).collect(Collectors.toList());

            BigInteger firstNumber = BigInteger.valueOf(numbers.removeFirst());
            List<Character> validCombination = new ArrayList<>();
            if (calculateResults(operators, requiredResult, firstNumber, numbers, validCombination)) {
                totalCalibrationResult = totalCalibrationResult.add(requiredResult);
            }
        }

        return totalCalibrationResult;
    }

    private boolean calculateResults(char[] operators, BigInteger requiredResult, BigInteger result, List<Long> remainingNumbers, List<Character> validOperations) {
        BigInteger nextNumber = BigInteger.valueOf(remainingNumbers.removeFirst());

        for (char operator : operators) {
            BigInteger currentResult = calculate(result, nextNumber, operator);

            if (!remainingNumbers.isEmpty()) {
                if (calculateResults(operators, requiredResult, currentResult, new ArrayList<>(remainingNumbers), validOperations)) {
                    validOperations.add(operator);
                    return true;
                }
            }

            if (requiredResult.equals(currentResult) && remainingNumbers.isEmpty()) {
                validOperations.add(operator);
                return true;
            }
        }

        return false;
    }

    private BigInteger calculate(BigInteger result, BigInteger nextNumber, char operator) {
        if (operator == '+') {
            return result.add(nextNumber);
        } else if (operator == '*') {
            return result.multiply(nextNumber);
        } else if (operator == '|') {
            return new BigInteger(result.toString() + nextNumber.toString());
        } else {
            throw new RuntimeException("Incorrect operator given");
        }
    }

    BigInteger partTwo(String input) {
        String[] lines = input.split("\r?\n");

        char[] operators = {'+', '*', '|'};

        BigInteger totalCalibrationResult = BigInteger.ZERO;
        for (String line : lines) {
            String[] resultNumberSplit = line.split(":\\s");
            BigInteger requiredResult = new BigInteger(resultNumberSplit[0]);
            List<Long> numbers = Arrays.stream(resultNumberSplit[1].split("\\s")).map(Long::parseLong).collect(Collectors.toList());

            List<Long> numberCopy = new ArrayList<>(numbers);
            BigInteger firstNumber = BigInteger.valueOf(numberCopy.removeFirst());
            List<Character> validCombination = new ArrayList<>();
            if (calculateResults(operators, requiredResult, firstNumber, numberCopy, validCombination)) {
                totalCalibrationResult = totalCalibrationResult.add(requiredResult);
            }
        }

        return totalCalibrationResult;
    }

}