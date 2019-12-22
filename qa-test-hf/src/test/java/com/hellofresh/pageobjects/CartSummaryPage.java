package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CartSummaryPage extends BasicPageObject {

    @FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
    WebElement proceedToCheckOutButton;

    public CartSummaryPage(){
        //This initElements method will create all WebElements
        PageFactory.initElements(pageFactory, this);
    }

    @Step
    public AddressDetailsPage clickProceedToCheckOutButton() {
        browser_actions.click(proceedToCheckOutButton);
        return new AddressDetailsPage();
    }
}
