package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ShippingDetailsPage extends BasicPageObject {

    @FindBy(id = "uniform-cgv")
    WebElement termsAndConditionsCheckBox;

    @FindBy(name = "processCarrier")
    WebElement proceedToCheckoutButton;

    public ShippingDetailsPage(){
        //This initElements method will create all WebElements
        AjaxElementLocatorFactory pageFactory = new AjaxElementLocatorFactory(Driver.getDriver(), CommonConstants.VISIBILITY_TIMEOUT);
        PageFactory.initElements(pageFactory, this);
    }

    @Step
    public PaymentDetailsPage clickProceedToCheckoutButton() {
        browser_actions.click(proceedToCheckoutButton);
        return new PaymentDetailsPage();
    }

    @Step
    public void checkTermsAndConditionsCheckBox() {
        browser_actions.click(termsAndConditionsCheckBox);
    }
}
