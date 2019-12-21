package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SignInLandingPage extends BasicPageObject {

    @FindBy(css = "h1")
    WebElement headingLabel;

    @FindBy(className = "account")
    WebElement accountLabel;

    @FindBy(className = "info-account")
    WebElement accountInfoLabel;

    @FindBy(className = "logout")
    WebElement logoutLink;

    @FindBy(linkText = "Women")
    WebElement womenTabLink;


    public SignInLandingPage(){
        //This initElements method will create all WebElements
        AjaxElementLocatorFactory pageFactory = new AjaxElementLocatorFactory(Driver.getDriver(), CommonConstants.VISIBILITY_TIMEOUT);
        PageFactory.initElements(pageFactory, this);
    }

    @Step
    public String getHeadingText() {
        return browser_actions.getText(headingLabel);
    }

    @Step
    public String getAccountLabelText() {
        return browser_actions.getText(accountLabel);
    }

    @Step
    public String getAccountInfoLabelText() {
        return browser_actions.getText(accountInfoLabel);
    }

    @Step
    public Boolean isLogoutVisible() {
        return isElementDisplayed(logoutLink);
    }

    @Step
    public ProductListPage clickwomenTabLink() {
        browser_actions.click(womenTabLink);
        return new ProductListPage();
    }
}
