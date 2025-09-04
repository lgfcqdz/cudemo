package com.cucumber.mydemo.stepdefinitions.baidu;

import com.cucumber.mydemo.core.BaseSteps;
import com.cucumber.mydemo.core.WaitUtils;
import com.cucumber.mydemo.pages.baidu.MainPage;
import com.cucumber.mydemo.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class baiduSteps extends BaseSteps {
    private MainPage mainPage;

    public baiduSteps() {
        this.mainPage = new MainPage();
    }

    @Given("Open the baidu page.")
    public void openBaiduPage() {
        driver.get(ConfigReader.getBaseUrl());
        WaitUtils.waitPageLoadingCompleted(driver);
        WaitUtils.waitForVisibility(driver,mainPage.chatSubmitButton, 3000);
    }

    @When("I enter search term {string}")
    public void iEnterSearchTerm(String searchTerm) {
        mainPage.searchProblem.sendKeys(searchTerm);
    }

    @When("I click the search button")
    public void iClickTheSearchButton() {
        mainPage.chatSubmitButton.click();
    }

    @Then("Results containing {string} should be displayed")
    public void checkResult(String expectedText) {
        WebElement results = driver.findElement(By.id("content_left"));
        assertTrue(results.getText().contains(expectedText));
    }
}
