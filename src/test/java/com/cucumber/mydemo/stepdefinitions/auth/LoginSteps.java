package com.cucumber.mydemo.stepdefinitions.auth;

import com.cucumber.mydemo.core.DriverManager;
import com.cucumber.mydemo.pages.auth.LoginPage;
import com.cucumber.mydemo.utils.ConfigReader;
import io.cucumber.java.en.Given;

public class LoginSteps {
    private LoginPage loginPage;
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        DriverManager.getDriver().get(ConfigReader.getBaseUrl() + "/login");
        loginPage = new LoginPage();
    }

//    @When("I login with username {string} and password {string}")
//    public void i_login_with_username_and_password(String username, String password) {
//        loginPage.login(username, password);
//        homePage = new HomePage(DriverManager.getDriver());
//    }
//
//    @Then("I should be on the home page")
//    public void i_should_be_on_the_home_page() {
//        Assert.assertTrue(homePage.isDashboardDisplayed());
//    }
}