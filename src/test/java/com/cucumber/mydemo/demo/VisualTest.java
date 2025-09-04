package com.cucumber.mydemo.demo;

import com.cucumber.mydemo.core.DriverManager;
import com.cucumber.mydemo.core.WaitUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class VisualTest{
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver 初始化失败");
        }
        driver.manage().window().maximize();
    }

    @Test
    public void testPageScreenshot() throws IOException {

        driver.get("https://www.baidu.com");
        WaitUtils.waitPageLoadingCompleted(driver);
        WebElement element = driver.findElement(By.className("san-card"));

        // 确保元素可见
        WaitUtils.waitForVisibility(driver, element, 5000);
        driver.navigate().refresh();

        // 获取元素截图
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver, element);

        // 与预期图片比对
        BufferedImage expected = ImageIO.read(
                new File("src/test/resources/images/sikuli/baidu/baiduSearchButton.png")
        );
        ImageDiff diff = new ImageDiffer().makeDiff(expected, screenshot.getImage());
        if (diff.hasDiff()) {
            // 保存差异标记图
            ImageIO.write(diff.getMarkedImage(), "PNG", new File("diff_marked.png"));
            // 保存实际截图
            ImageIO.write(screenshot.getImage(), "PNG", new File("actual.png"));
            // 保存差异图
            ImageIO.write(diff.getDiffImage(), "PNG", new File("diff.png"));
        }

        assertFalse(diff.hasDiff(), "发现视觉差异，请查看 diff_marked.png");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
