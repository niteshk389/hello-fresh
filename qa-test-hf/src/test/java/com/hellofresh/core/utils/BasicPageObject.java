package com.hellofresh.core.utils;

import com.hellofresh.core.actions.BrowserActions;
import com.hellofresh.core.actions.PageActions;
import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Class to be extended for all page objects
 */
public class BasicPageObject {
    protected AjaxElementLocatorFactory pageFactory;
    private PropertyReader properties = PropertyReader.getInstance();
    protected PageActions browser_actions = new BrowserActions();

    public BasicPageObject() {
         pageFactory = new AjaxElementLocatorFactory(Driver.getDriver(), Integer.parseInt(properties.getProperty("driver.visibility.timeout.seconds")));
    }

    public void openAppHomePage() {
        String url = properties.getProperty(CommonConstants.APP_URL_CONFIG);
        Driver.getDriver().get(url);
    }

    public String getCurrentUrl() {
        return Driver.getDriver().getCurrentUrl();
    }

    public Boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }
}
