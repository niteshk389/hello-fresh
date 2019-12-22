package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductDetailsPage extends BasicPageObject {

    @FindBy(name = "Submit")
    WebElement addToCartButton;

    @FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
    WebElement proceedToCheckoutButton;

    public ProductDetailsPage(){
        //This initElements method will create all WebElements
        PageFactory.initElements(pageFactory, this);
    }

    @Step
    public void clickAddToCartButton() {
        browser_actions.click(addToCartButton);
    }

    @Step
    public CartSummaryPage clickProceedToCheckoutButton() {
        browser_actions.click(proceedToCheckoutButton);
        return new CartSummaryPage();
    }
}
