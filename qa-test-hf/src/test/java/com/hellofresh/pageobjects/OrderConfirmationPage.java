package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrderConfirmationPage extends BasicPageObject {

    @FindBy(css = "h1")
    WebElement heading;

    @FindBy(xpath = "//li[@id='step_end' and @class='step_current last']")
    WebElement paymentChevronLabel;

    @FindBy(xpath = "//*[@class='cheque-indent']/strong")
    WebElement orderCompleteLabel;

    @FindBy(xpath = "//li[@class='step_done step_done_last four']")
    WebElement shipingChevronLabel;

    public OrderConfirmationPage(){
        //This initElements method will create all WebElements
        PageFactory.initElements(pageFactory, this);
    }

    @Step
    public String getHeadingText() {
        return browser_actions.getText(heading);
    }

    @Step
    public boolean isShipingChevronDisplayed() {
        return isElementDisplayed(shipingChevronLabel);
    }

    @Step
    public boolean isPaymentChevronDisplayed() {
        return isElementDisplayed(paymentChevronLabel);
    }

    @Step
    public String getOrderConfirmationText() {
        return browser_actions.getText(orderCompleteLabel);
    }
}
