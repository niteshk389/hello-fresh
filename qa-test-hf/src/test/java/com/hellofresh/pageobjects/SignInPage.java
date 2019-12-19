package com.hellofresh.pageobjects;

import com.hellofresh.core.utils.BasicPageObject;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasicPageObject {

    @FindBy(id = "email")
    WebElement emailTextField;

    @FindBy(id = "passwd")
    WebElement passwordTextField;

    @FindBy(id = "SubmitLogin")
    WebElement loginButton;

    @FindBy(className = "account")
    WebElement accountNameLabel;

    @FindBy(className = "info-account")
    WebElement accountInfoLabel;

    @FindBy(className = "logout")
    WebElement logoutLink;

    @FindBy(id = "email_create")
    WebElement registrationEmailTextField;

    @FindBy(id = "SubmitCreate")
    WebElement createAccountButton;

    public SignInPage(){
        //This initElements method will create all WebElements
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @Step
    public void enterEmailAddress(String emailId) {
        browser_actions.enterText(emailTextField, emailId);
    }

    @Step
    public void enterPassword(String password) {
        browser_actions.enterText(passwordTextField, password);
    }

    @Step
    public HomePage clickSignInButton() {
        browser_actions.click(loginButton);
        return new HomePage();
    }

    @Step
    public void enterRegistrationEmail(String emailId) {
        browser_actions.enterText(registrationEmailTextField, emailId);
    }

    @Step
    public RegistrationPage clickCreateAccountButton() {
        browser_actions.click(createAccountButton);
        return new RegistrationPage();
    }
}
