package com.cucumber.mydemo.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderUtil {

    public static String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static JSONObject parseJSONObjectFile(String filePath) throws IOException {
        String content = readFileAsString(filePath);
        return new JSONObject(content);
    }

    public static JSONArray parseJSONArrayFile(String filePath) throws IOException {
        String content = readFileAsString(filePath);
        return new JSONArray(content);
    }
}