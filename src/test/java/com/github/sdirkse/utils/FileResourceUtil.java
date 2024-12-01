package com.github.sdirkse.utils;

import java.io.IOException;
import java.util.Objects;

public class FileResourceUtil {

    public String readFile(String filePath) {
        String contents = "";
        try {
            contents = new String(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath)).readAllBytes());
        } catch(IOException e) {
            contents = "";
        }

        return contents;
    }

}
