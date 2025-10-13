package com.cuber.dmtest.utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ScreenshotUtil {
    // 截图保存目录 - 可从配置文件中读取
    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/dmtest-output/screenshots/";
    /**
     * 捕获截图并保存到指定目录
     * @param driver WebDriver实例
     * @param testName 测试名称（用于命名截图文件）
     * @return 截图文件的完整路径
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            // 确保目录存在
            File directory = new File(SCREENSHOT_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 生成时间戳和文件名
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timeStamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;

            // 捕获截图
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));

            return filePath;
        } catch (IOException e) {
            System.err.println("截图保存失败: " + e.getMessage());
            return null;
        }
    }

}
