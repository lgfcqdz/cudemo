package com.cuber.dmtest.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static void waitPageLoadingCompleted(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver 不能为 null");
        }

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .until(d -> {
                    return ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState").equals("complete");
                });
    }

    public static void waitForVisibility(WebDriver driver, WebElement element, long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibilityWithScroll(WebDriver driver, WebElement element, long timeout) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
        waitForVisibility(driver, element, timeout);
    }
}
