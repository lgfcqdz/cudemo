package com.cucumber.mydemo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final String PROPERTIES_FILE = "config/config.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream == null) {
                throw new RuntimeException("Unable to find configuration file: " + PROPERTIES_FILE);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static ConfigReader getInstance() {
        return new ConfigReader();
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    // 在ConfigReader中添加方法
    public static String getScreenshotDir() {
        return properties.getProperty("screenshot.dir",
                System.getProperty("user.dir") + "/test-output/screenshots/");
    }

    // 修改ScreenshotUtil类
    private static final String SCREENSHOT_DIR = ConfigReader.getScreenshotDir();
}