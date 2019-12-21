package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PaymentDetailsPage extends BasicPageObject {

    @FindBy(className = "bankwire")
    WebElement bankWirePaymentLink;

    public PaymentDetailsPage(){
        //This initElements method will create all WebElements
        AjaxElementLocatorFactory pageFactory = new AjaxElementLocatorFactory(Driver.getDriver(), CommonConstants.VISIBILITY_TIMEOUT);
        PageFactory.initElements(pageFactory, this);
    }

    @Step
    public ConfirmYourOrderPage clickBankWirePaymentLink() {
        browser_actions.click(bankWirePaymentLink);
        return new ConfirmYourOrderPage();
    }
}
