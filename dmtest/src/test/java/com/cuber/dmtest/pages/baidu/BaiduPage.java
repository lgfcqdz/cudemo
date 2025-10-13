package com.cuber.dmtest.pages.baidu;

import com.cuber.dmtest.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class BaiduPage extends BasePage {
    @FindBy(css = "#kw")
    private WebElement searchInput;

    @FindBy(css = "#su")
    private WebElement searchButton;

    public void enterSearchKeyword(String keyword) {
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
