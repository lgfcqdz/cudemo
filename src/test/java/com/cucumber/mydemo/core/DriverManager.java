package com.cucumber.mydemo.core;

import com.cucumber.mydemo.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static WebDriver driver;
    private static Properties config = ConfigReader.getInstance().getProperties(); // 如果使用Config
    private static String DRIVER_PATH = "src/test/resources/drivers/";
    private DriverManager() {
    }
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
    private static WebDriver initializeDriver() {
        if (driver == null) {
            String browser = config.getProperty("browser");
            boolean headless = Boolean.parseBoolean(config.getProperty("headless"));

            switch (browser.toLowerCase()) {
                case "chrome":

                    System.setProperty("webdriver.chrome.driver", DRIVER_PATH + "chromedriver.exe");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--disable-extensions");
                    if (headless) chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    System.setProperty("webdriver.chrome.driver", DRIVER_PATH + "firefoxdriver.exe");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) firefoxOptions.addArguments("-headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                // Add other browsers as needed
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 全局隐式等待 (谨慎使用)
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}