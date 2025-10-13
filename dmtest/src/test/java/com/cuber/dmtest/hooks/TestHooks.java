package com.cuber.dmtest.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestHooks {
    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);

    private final WebDriver driver;

    // 通过构造函数注入，确保单例
    public TestHooks(WebDriver driver) {
        this.driver = driver;
    }

    @Before
    public void setUp() {
        // 可以在这里做一些初始化操作
        driver.manage().window().maximize();
    }
//    @After
//    public void tearDown() {
//        // 清理操作，但不要quit driver
//        driver.manage().deleteAllCookies();
//    }

//    @After(order = 1)
//    public void teardown(Scenario scenario) {
//        System.out.println("After 1");
//        if (scenario.isFailed()) {
//            // 使用场景名称作为截图文件名
//            String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
//            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, scenarioName);
//
//            // 将截图附加到Cucumber报告（如果使用Cucumber报告）
//            if (screenshotPath != null) {
//                try {
//                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//                    scenario.attach(screenshot, "image/png", scenarioName);
//                } catch (Exception e) {
//                    System.err.println("无法将截图附加到报告: " + e.getMessage());
//                }
//            }
//
//            System.out.println("场景执行失败，截图已保存至: " + screenshotPath);
//            DriverManager.quitDriver();
//        }
//    }
    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // 截图处理
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName() + "_failure");
            } catch (Exception e) {
                logger.error("Failed to take screenshot", e);
            }
        }

        // 关闭浏览器
        if (driver != null) {
            driver.quit();
        }

        logger.info("Finished scenario: {} - Status: {}",
                scenario.getName(), scenario.getStatus());
    }
}
