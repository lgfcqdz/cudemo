package com.cuber.dmtest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Set;

public abstract class BasePage {

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;
    // 页面加载超时时间（秒）
    private static final int PAGE_LOAD_TIMEOUT = 30;

    // 构造函数，可以初始化页面元素
    public BasePage() {
    }

    @PostConstruct
    protected void initElements() {
        if (driver != null) {
            PageFactory.initElements(driver, this);
        }
    }

    // 1. 导航到指定URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // 2. 获取页面标题
    public String getPageTitle() {
        return driver.getTitle();
    }

    // 3. 获取当前URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // 4. 等待页面完全加载
    public void waitForPageToLoad() {
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    // 5. 等待元素可见
    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 6. 等待元素可点击
    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // 7. 点击元素
    public void click(By locator) {
        waitForElementClickable(locator);
        driver.findElement(locator).click();
    }

    public void click(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }

    // 8. 输入文本
    public void type(By locator, String text) {
        waitForElementVisible(locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void type(WebElement element, String text) {
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    // 9. 获取元素文本
    public String getText(By locator) {
        waitForElementVisible(locator);
        return driver.findElement(locator).getText();
    }

    public String getText(WebElement element) {
        waitForElementVisible(element);
        return element.getText();
    }

    // 10. 判断元素是否显示
    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // 11. 判断元素是否存在
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // 12. 切换到iframe
    public void switchToFrame(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void switchToFrame(WebElement element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    // 13. 切换回默认内容
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // 14. 执行JavaScript
    public Object executeJavaScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    // 15. 滚动到元素
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElement(WebElement element) {
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
    }

    // 16. 获取页面截图
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // 17. 刷新页面
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // 18. 返回上一页
    public void goBack() {
        driver.navigate().back();
    }

    // 19. 前进下一页
    public void goForward() {
        driver.navigate().forward();
    }

    // 20. 接受弹窗
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    // 21. 取消弹窗
    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    // 22. 获取弹窗文本
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    // 23. 在弹窗中输入文本
    public void typeInAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }

    // 24. 鼠标悬停
    public void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // 25. 获取所有窗口句柄
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    // 26. 切换到指定窗口
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    // 27. 关闭当前窗口并切换回主窗口
    public void closeCurrentWindowAndSwitchToMain() {
        String mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }

        driver.switchTo().window(mainWindow);
    }

    // 28. 等待指定时间（毫秒）
    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 29. 检查元素是否包含特定文本
    public boolean isTextPresentInElement(By locator, String text) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isTextPresentInElement(WebElement element, String text) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (TimeoutException e) {
            return false;
        }
    }

    // 30. 日志记录方法
    protected void logInfo(String message) {
        // 这里可以使用您项目中的日志框架，例如SLF4J
        System.out.println("INFO: " + message);
    }

    protected void logError(String message) {
        System.err.println("ERROR: " + message);
    }

    protected void logDebug(String message) {
        System.out.println("DEBUG: " + message);
    }
}
