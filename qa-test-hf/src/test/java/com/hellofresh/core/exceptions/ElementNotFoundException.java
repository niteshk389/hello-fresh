package com.hellofresh.core.exceptions;


import com.hellofresh.core.listeners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(Throwable cause) {
        super("Element not present on webpage", cause);
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
                cause.getLocalizedMessage());;
    }
}
