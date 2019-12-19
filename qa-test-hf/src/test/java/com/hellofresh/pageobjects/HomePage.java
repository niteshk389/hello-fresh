package com.hellofresh.pageobjects;

import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasicPageObject {

    @FindBy(className = "login")
    WebElement loginLink;

    public HomePage(){
        //This initElements method will create all WebElements
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @Step
    public SignInPage clickLoginLink() {
        browser_actions.click(loginLink);
        return new SignInPage();
    }
}
