package com.cucumber.mydemo.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // 等待元素可见
    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 等待元素可点击
    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // 点击元素
    protected void click(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    // 输入文本
    protected void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    // 获取文本
    protected String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    // 执行JavaScript点击（用于解决某些点击问题）
    protected void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    // 等待元素可见
    public WebElement waitForElementVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // 等待元素可点击
    public WebElement waitForElementClickable(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // 检查元素是否存在
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // 检查元素是否可见
    public boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // 安全的点击操作
    public void safeClick(By locator) {
        WebElement element = waitForElementClickable(locator, 10);
        element.click();
    }

    // 安全的输入操作
    public void safeSendKeys(By locator, String text) {
        WebElement element = waitForElementVisible(locator, 10);
        element.clear();
        element.sendKeys(text);
    }

    // 获取元素文本
    public String getElementText(By locator) {
        WebElement element = waitForElementVisible(locator, 10);
        return element.getText();
    }

    // 获取元素属性
    public String getElementAttribute(By locator, String attribute) {
        WebElement element = waitForElementVisible(locator, 10);
        return element.getAttribute(attribute);
    }
    // 验证页面标题
    public boolean verifyPageTitle(String expectedTitle) {
        return driver.getTitle().contains(expectedTitle);
    }

    // 验证页面URL
    public boolean verifyPageUrl(String expectedUrl) {
        return driver.getCurrentUrl().contains(expectedUrl);
    }

    // 刷新页面
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // 返回上一页
    public void goBack() {
        driver.navigate().back();
    }


    // 使用JS点击元素
    public void jsClick(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // 使用JS滚动到元素
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // 使用JS设置元素值
    public void jsSetValue(By locator, String value) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, value);
    }
}
