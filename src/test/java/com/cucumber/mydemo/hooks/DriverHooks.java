package com.cucumber.mydemo.hooks;

import com.cucumber.mydemo.core.DriverManager;
import com.cucumber.mydemo.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class DriverHooks {
    protected WebDriver driver;

    @Before(order = 1)
    public void setup(Scenario scenario) {
        if (driver == null) {
            driver= DriverManager.getDriver();
        }
    }

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

    @After(order = 999) // 最后执行
    public void quitDriver(Scenario scenario) {
        System.out.println("After 999");
        DriverManager.quitDriver();
    }
}
