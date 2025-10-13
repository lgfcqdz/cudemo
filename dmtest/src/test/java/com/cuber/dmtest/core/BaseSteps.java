package com.cuber.dmtest.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

public class BaseSteps {
    @Autowired
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseSteps() {
//        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
