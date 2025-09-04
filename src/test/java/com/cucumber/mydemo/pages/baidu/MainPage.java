package com.cucumber.mydemo.pages.baidu;

import com.cucumber.mydemo.core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage() {
        super();
    }

    // 使用PageFactory初始化元素
    @FindBy(id = "chat-textarea")
    public WebElement searchProblem;

    @FindBy(id = "chat-submit-button")
    public WebElement chatSubmitButton;

}
