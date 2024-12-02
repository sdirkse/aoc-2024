#!/bin/bash

usage() {
    echo "Usage: $0 <day_number>"
    echo "  - day_number: The number of the day to solve"
    exit 1
}

if [ $# -ne 1 ]; then
    usage
fi

CLASS_DIRECTORY="src/main/java/com/github/sdirkse"
TEST_DIRECTORY="src/test/java/com/github/sdirkse"
TEST_RESOURCE_DIRECTORY="src/test/resources"
SOURCE_CLASS_FILE="src/main/resources/day-class-template.java"
SOURCE_TEST_FILE="src/main/resources/day-test-template.java"
DAY_NUMBER="$1"

if ! [[ "$DAY_NUMBER" =~ ^[0-9]+$ ]]; then
    echo "Error: Day number must be a positive integer."
    exit 1
fi
if [ ! -f "$SOURCE_CLASS_FILE" ]; then
    echo "Error: Source class file 'SOURCE_CLASS_FILE' does not exist."
    exit 1
fi
if [ ! -f "$SOURCE_TEST_FILE" ]; then
    echo "Error: Source test file 'SOURCE_TEST_FILE' does not exist."
    exit 1
fi

DEST_CLASS_FILE="$CLASS_DIRECTORY/Day${DAY_NUMBER}.java"
DEST_TEST_FILE="$TEST_DIRECTORY/Day${DAY_NUMBER}Test.java"
DEST_INPUT_FILE="$TEST_RESOURCE_DIRECTORY/day${DAY_NUMBER}.txt"

if [ -f "$DEST_CLASS_FILE" ]; then
    echo "Error: Destination class file '$DEST_CLASS_FILE' already exists!"
    exit 1
fi
if [ -f "$DEST_TEST_FILE" ]; then
    echo "Error: Destination class file '$DEST_TEST_FILE' already exists!"
    exit 1
fi

mkdir -p "$(dirname "$DEST_CLASS_FILE")"
mkdir -p "$(dirname "$DEST_TEST_FILE")"
mkdir -p "$(dirname "$DEST_INPUT_FILE")"

sed "s/DAY_NUMBER/$DAY_NUMBER/g" "$SOURCE_CLASS_FILE" > "$DEST_CLASS_FILE"
sed "s/DAY_NUMBER/$DAY_NUMBER/g" "$SOURCE_TEST_FILE" > "$DEST_TEST_FILE"
touch $DEST_INPUT_FILE

if [ $? -eq 0 ]; then
    echo "File successfully copied and modified:"
    echo "  Replaced 'DAY_NUMBER' with '$DAY_NUMBER'"
    echo "  --- CLASS ---"
    echo "  From: $SOURCE_CLASS_FILE"
    echo "  To:   $DEST_CLASS_FILE"
    echo "  --- TEST ---"
    echo "  From: $SOURCE_TEST_FILE"
    echo "  To:   $DEST_TEST_FILE"
    echo "  --- INPUT ---"
    echo "  Created: $DEST_INPUT_FILE"
else
    echo "Error: Failed to copy or modify the file."
    exit 1
fi