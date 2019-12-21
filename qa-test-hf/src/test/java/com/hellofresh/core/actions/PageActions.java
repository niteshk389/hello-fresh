package com.hellofresh.core.actions;

import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.exceptions.ElementNotFoundException;
import com.hellofresh.core.listeners.ExtentTestManager;
import com.hellofresh.core.utils.PropertyReader;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class containing actions which are common across browsers and mobiles
 */
public abstract class PageActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageActions.class);
    PropertyReader properties = PropertyReader.getInstance();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Integer.parseInt(properties.getProperty("driver.clickability.timeout.seconds")));

    /**
     * Method to click a webelement
     *
     * @param element: Web element to perform click action on
     */
    public void click(WebElement element) {
        try {
            waitForElementToBeClickable(element);
            element.click();
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(e);
        }
    }

    /**
     * Method to type a webelement
     *
     * @param element: Web element to perform type action on
     */
    public void enterText(WebElement element, String text) {
        try {
            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(e);
        }
    }

    /**
     * Method to select an option for a webelement
     *
     * @param element: Web element to perform select action on
     */
    public void selectOption(WebElement element, String options) {
        try {
            Select select = new Select(element);
            select.selectByValue(options);
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(e);
        }
    }

    /**
     * Method to select value in dropdown a webelement
     *
     * @param element: Web element to perform select action on
     */
    public void selectByVisibleText(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(e);
        }
    }

    /**
     * Method to getText for a webelement
     *
     * @param element: Web element to get text for a element
     */
    public String getText(WebElement element) {
        try {
            return element.getText();
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(e);
        }
    }

    /**
     * Method to wait for visibility of webelement
     *
     * @param element: Web element
     */
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Method to wait for a webelement to be clickable
     *
     * @param element: Web element to wait until its clickable
     */
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Method to hover a webelement
     *
     * @param element: Web element to perform hover action on
     */
    public void hoverOverElement(WebElement element) {
        try {
            Actions action = new Actions(Driver.getDriver());
            action.moveToElement(element).build();
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(e);
        }
    }
}
