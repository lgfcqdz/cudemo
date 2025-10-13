package com.cuber.dmtest.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@ComponentScan(basePackages = {"com.cuber.dmtest.pages", "com.cuber.dmtest.stepdefinitions"})
public class TestConfig {
    @Value("${browser.name}")
    private String browser;

    @Value("${browser.headless}")
    private boolean headless;

    @Value("${browser.implicitWait}")
    private int implicitWait;

    @Value("${browser.explicitWait}")
    private int explicitWait;

    @Value("${browser.architecture}")
    private String architecture;

    @Bean
    @Scope("singleton")
    public WebDriver webDriver() {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
//                // 新的API调用方式
//                WebDriverManager manager = WebDriverManager.chromedriver();
//
//                // 设置架构
//                if ("X86".equalsIgnoreCase(architecture)) {
//                    manager.architecture(Architecture.X32);
//                } else {
//                    manager.architecture(Architecture.X64);
//                }
//
//                manager.setup();
//
//                ChromeOptions options = new ChromeOptions();
//                if (headless) {
//                    options.addArguments("--headless");
//                }
//                options.addArguments("--no-sandbox");
//                options.addArguments("--disable-dev-shm-usage");
//                options.addArguments("--window-size=1920,1080");
//                options.addArguments("--disable-popup-blocking");
//                options.addArguments("--disable-blink-features=AutomationControlled");
//                options.addArguments("--remote-allow-origins=*");
//                options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//                options.setExperimentalOption("useAutomationExtension", false);
//                driver = new ChromeDriver(options);// 设置系统属性，直接指定 ChromeDriver 路径
                String driverPath = getClass().getClassLoader().getResource("drivers/chromedriver.exe").getPath();
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions options = new ChromeOptions();
                // 可选：添加一些常用选项
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--remote-allow-origins=*");
                options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
                options.setExperimentalOption("useAutomationExtension", false);
                driver = new ChromeDriver(options);

                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        return driver;
    }

    @Bean
    @Scope("singleton")
    public WebDriverWait webDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    }
}
