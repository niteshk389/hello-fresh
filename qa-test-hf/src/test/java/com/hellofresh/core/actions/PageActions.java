package com.hellofresh.core.actions;

import com.hellofresh.core.constants.Browsers;
import com.hellofresh.core.constants.CommonConstants;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class PageActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageActions.class);

    public void click(WebElement element) {
        element.click();
    }

    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void selectOption(WebElement element, String options) {
        Select select = new Select(element);
        select.selectByValue(options);
    }

    public String getText(WebElement element) {
        return element.getText();
    }
}
