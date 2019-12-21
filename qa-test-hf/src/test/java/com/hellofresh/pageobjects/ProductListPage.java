package com.hellofresh.pageobjects;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.Step;
import com.hellofresh.core.utils.BasicPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductListPage extends BasicPageObject {

    @FindBy(xpath = "//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
    WebElement tShirtLink;

    public ProductListPage(){
        //This initElements method will create all WebElements
        AjaxElementLocatorFactory pageFactory = new AjaxElementLocatorFactory(Driver.getDriver(), CommonConstants.VISIBILITY_TIMEOUT);
        PageFactory.initElements(pageFactory, this);
    }

    @Step
    public ProductDetailsPage clickTshirtLink() {
        browser_actions.click(tShirtLink);
        browser_actions.click(tShirtLink);
        return new ProductDetailsPage();
    }
}
