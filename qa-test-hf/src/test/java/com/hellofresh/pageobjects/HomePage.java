package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasicPageObject {

    @FindBy(className = "login")
    WebElement loginLink;

    public HomePage(){
        //This initElements method will create all WebElements
        AjaxElementLocatorFactory pageFactory = new AjaxElementLocatorFactory(Driver.getDriver(), CommonConstants.VISIBILITY_TIMEOUT);
        PageFactory.initElements(pageFactory, this);
    }

    @Step
    public SignInPage clickLoginLink() {
        browser_actions.click(loginLink);
        return new SignInPage();
    }
}
