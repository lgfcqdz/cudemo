package com.cuber.dmtest.stepdefinitions.baidu;

import com.cuber.dmtest.pages.baidu.BaiduPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class BaiduSteps {
    @Value("${environment.baseUrl}")
    private String baseUrl;
    @Autowired
    private WebDriver driver;
    @Autowired
    private BaiduPage baiduPage;
    @Given("用户打开百度首页")
    public void navigateToBaiduHomepage() {
        baiduPage.navigateTo(baseUrl);
        baiduPage.waitForPageToLoad();
    }

    @When("用户在搜索框中输入 {string}")
    public void userEntersKeywordInSearchBox(String keyword) {
        baiduPage.enterSearchKeyword(keyword);
        baiduPage.clickSearchButton();
        baiduPage.waitForPageToLoad();
    }

    @Then("页面标题应包含 {string}")
    public void pageTitleShouldContainKeyword(String expectedKeyword) {
        String actualTitle = baiduPage.getPageTitle();
        assertTrue("页面标题应包含: " + expectedKeyword,
                actualTitle.contains(expectedKeyword));
    }
}
