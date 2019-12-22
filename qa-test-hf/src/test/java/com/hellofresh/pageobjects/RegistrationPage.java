package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.testdata.TestDataGenerator;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Map;

public class RegistrationPage  extends BasicPageObject {

    @FindBy(id = "email")
    WebElement emailTextField;

    @FindBy(id = "customer_firstname")
    WebElement firstNameTextField;

    @FindBy(id = "customer_lastname")
    WebElement lastNameTextField;

    @FindBy(id = "passwd")
    WebElement passwordTextField;

    @FindBy(id = "days")
    WebElement daysSelect;

    @FindBy(id = "months")
    WebElement monthsSelect;

    @FindBy(id = "years")
    WebElement yearsSelect;

    @FindBy(id = "company")
    WebElement companyTextField;

    @FindBy(id = "address1")
    WebElement address1TextField;

    @FindBy(id = "address2")
    WebElement address2TextField;

    @FindBy(id = "city")
    WebElement cityTextField;

    @FindBy(id = "id_state")
    WebElement idStateSelect;

    @FindBy(id = "postcode")
    WebElement postcodeTextField;

    @FindBy(id = "other")
    WebElement otherTextField;

    @FindBy(id = "phone")
    WebElement phoneTextField;

    @FindBy(id = "phone_mobile")
    WebElement phoneMobileTextField;

    @FindBy(id = "alias")
    WebElement aliasTextField;

    @FindBy(id = "submitAccount")
    WebElement submitAccountButton;

    @FindBy(id = "submitAccount")
    WebElement genderRadioButton;

    public RegistrationPage() {
        PageFactory.initElements(pageFactory, this);
    }

    public void enterRegistrationDetails(Map<String, String> userDetails){
        browser_actions.waitForVisibility(genderRadioButton);
        browser_actions.enterText(firstNameTextField, userDetails.get("firstname"));
        browser_actions.enterText(lastNameTextField, userDetails.get("lastname"));
        browser_actions.enterText(passwordTextField, userDetails.get("password"));
        browser_actions.selectOption(daysSelect, userDetails.get("day"));
        browser_actions.selectOption(monthsSelect, userDetails.get("month"));
        browser_actions.selectOption(yearsSelect, userDetails.get("year"));
        browser_actions.enterText(companyTextField, userDetails.get("company"));
        browser_actions.enterText(address1TextField, userDetails.get("address1"));
        browser_actions.enterText(address2TextField, userDetails.get("address2"));
        browser_actions.enterText(cityTextField, userDetails.get("city"));
        browser_actions.selectByVisibleText(idStateSelect, userDetails.get("idState"));
        browser_actions.enterText(postcodeTextField, userDetails.get("postcode"));
        browser_actions.enterText(otherTextField, userDetails.get("other"));
        browser_actions.enterText(phoneTextField, userDetails.get("phone"));
        browser_actions.enterText(phoneMobileTextField, userDetails.get("phoneMobile"));
        browser_actions.enterText(aliasTextField, userDetails.get("alias"));
    }

    public SignInLandingPage clickSubmitAccountButton() {
        browser_actions.click(submitAccountButton);
        return new SignInLandingPage();
    }
}
