package com.github.sdirkse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class Day17 {

    private long registerA;
    private long registerB;
    private long registerC;
    private List<Long> output = new ArrayList<>();

    String partOne(String input) {
        String[] lines = input.split("\r?\n");
        registerA = Integer.parseInt(lines[0].substring(12));
        registerB = Integer.parseInt(lines[1].substring(12));
        registerC = Integer.parseInt(lines[2].substring(12));
        List<Integer> instructions = Arrays.stream(lines[4].substring(9).split(",")).map(Integer::parseInt).toList();

        doCalculations(instructions);

        System.out.println("Register A: " + registerA);
        System.out.println("Register B: " + registerB);
        System.out.println("Register C: " + registerC);

        return output.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    private void doCalculations(List<Integer> instructions) {
        for (int i = 0; i < instructions.size(); i += 2) {
            int newInstructionPointer = handleInstruction(instructions.get(i), instructions.get(i + 1));

            if (newInstructionPointer != -1) {
                i = newInstructionPointer - 2;
            }
        }
    }

    private int handleInstruction(int opCode, int operand) {
        switch (opCode) {
            case 0 -> registerA = (long) (registerA / Math.pow(2, getComboOperand(operand)));
            case 1 -> registerB = registerB ^ operand;
            case 2 -> registerB = getComboOperand(operand) % 8;
            case 3 -> {
                if (registerA != 0) {
                    return operand;
                }
            }
            case 4 -> registerB = registerB ^ registerC;
            case 5 -> output.add(getComboOperand(operand) % 8);
            case 6 -> registerB = (long) (registerA / Math.pow(2, getComboOperand(operand)));
            case 7 -> registerC = (long) (registerA / Math.pow(2, getComboOperand(operand)));
        }

        return -1;
    }

    private long getComboOperand(int operand) {
        return switch (operand) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> throw new RuntimeException("Unknown combo operand: " + operand);
        };
    }

    private List<Long> possibleRegisterAList = new ArrayList<>();

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");
        registerA = Integer.parseInt(lines[0].substring(12));
        registerB = Integer.parseInt(lines[1].substring(12));
        registerC = Integer.parseInt(lines[2].substring(12));
        List<Integer> instructions = Arrays.stream(lines[4].substring(9).split(",")).map(Integer::parseInt).toList();

        possibleRegisterAList.add(0L);

        instructions.reversed().forEach(desiredInstruction -> {
            List<Long> possibleSolution = new ArrayList<>();

            for (Long possibleRegisterA : possibleRegisterAList) {
                possibleSolution.addAll(processInstructions(possibleRegisterA, instructions, desiredInstruction));
            }

            possibleRegisterAList = possibleSolution;
        });

        return possibleRegisterAList.stream().mapToLong(Long::longValue).min().getAsLong();
    }

    private Collection<Long> processInstructions(Long possibleRegisterA, List<Integer> instructions, int desiredInstruction) {
        List<Long> possibleRegisterValues = new ArrayList<>();

        for (int add = 0; add < 7; add++) {
            output.clear();
            long newRegisterA = (possibleRegisterA << 3) | add;
            registerA = newRegisterA;

            doCalculations(instructions);

            if (output.getFirst().equals(Long.valueOf(desiredInstruction))) {
                possibleRegisterValues.add(newRegisterA);
            }
        }

        return possibleRegisterValues;
    }

}