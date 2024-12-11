package com.github.sdirkse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Day9 {

    long partOne(String input) {
        String[] lines = input.split("\r?\n");

        List<String> filesystem = new ArrayList<>();
        int[] numbers = Arrays.stream(lines[0].split("(?!^)")).mapToInt(Integer::parseInt).toArray();
        boolean isFile = true;
        int fileCounter = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (isFile) {
                for (int j = 0; j < numbers[i]; j++) {
                    filesystem.add(String.valueOf(fileCounter));
                }
                fileCounter++;
                isFile = false;
            } else {
                for (int j = 0; j < numbers[i]; j++) {
                    filesystem.add(".");
                }
                isFile = true;
            }
        }

        sortFilesystem(filesystem);
        List<Long> numericFilesystem = filesystem.stream().filter(s -> !s.equals(".")).map(Long::parseLong).toList();

        return calculateChecksum(numericFilesystem);
    }

    private void sortFilesystem(List<String> filesystem) {
        for (int rtl = filesystem.size() - 1; rtl >= 0; rtl--) {
            if (filesystem.get(rtl).equals(".")) continue;

            String number = filesystem.get(rtl);
            for (int ltr = 0; ltr < rtl; ltr++) {
                if (filesystem.get(ltr).equals(".")) {
                    filesystem.set(ltr, number);
                    filesystem.set(rtl, ".");
                    break;
                }
            }
        }
    }

    private long calculateChecksum(List<Long> filesystem) {
        long checksum = 0;

        for (int i = 0; i < filesystem.size(); i++) {
            checksum += i * filesystem.get(i);
        }

        return checksum;
    }

    private long calculateBlockChecksum(List<String> filesystem) {
        long checksum = 0;

        for (int i = 0; i < filesystem.size(); i++) {
            if (filesystem.get(i).equals(".")) continue;

            checksum += i * Long.parseLong(filesystem.get(i));
        }

        return checksum;
    }

    private void sortFilesystemByFile(List<String> filesystem) {
        int currentNumber = Integer.parseInt(filesystem.getLast());
        int startIndex = filesystem.size() - 1;
        int endIndex = filesystem.size() - 1;
        for (int rtl = filesystem.size() - 2; rtl >= 0; rtl--) {
            if (filesystem.get(rtl).equals(".")) {
                continue;
            }

            int fileNumber = Integer.parseInt(filesystem.get(rtl));
            if (fileNumber == currentNumber) {
                startIndex = rtl;
            } else {
                tryMoveFileBlock(filesystem, startIndex, endIndex);

                currentNumber = fileNumber;
                startIndex = rtl;
                endIndex = rtl;
            }
        }
    }

    private void tryMoveFileBlock(List<String> filesystem, int startIndex, int endIndex) {
        int blockSize = endIndex - startIndex + 1;
        int start = 0;
        int end = 0;

        boolean foundFreeSpace = false;
        for (int i = 0; i < startIndex; i++) {
            if (!filesystem.get(i).equals(".")) {
                foundFreeSpace = false;
                continue;
            }

            end = i;
            if (!foundFreeSpace) {
                start = i;
                foundFreeSpace = true;
            }
            if ((end - start + 1) == blockSize) {
                moveFileBlock(filesystem, startIndex, start, blockSize);
                break;
            }
        }
    }

    private void moveFileBlock(List<String> filesystem, int fromStartIndex, int toStartIndex, int blockSize) {
        for (int i = 0; i < blockSize; i++) {
            filesystem.set(toStartIndex + i, filesystem.get(fromStartIndex + i));
            filesystem.set(fromStartIndex + i, ".");
        }
    }

    long partTwo(String input) {
        String[] lines = input.split("\r?\n");

        List<String> filesystem = new ArrayList<>();
        int[] numbers = Arrays.stream(lines[0].split("(?!^)")).mapToInt(Integer::parseInt).toArray();
        boolean isFile = true;
        int fileCounter = 0;
        for (int number : numbers) {
            if (isFile) {
                for (int j = 0; j < number; j++) {
                    filesystem.add(String.valueOf(fileCounter));
                }
                fileCounter++;
                isFile = false;
            } else {
                for (int j = 0; j < number; j++) {
                    filesystem.add(".");
                }
                isFile = true;
            }
        }


        sortFilesystemByFile(filesystem);

        return calculateBlockChecksum(filesystem);
    }

}