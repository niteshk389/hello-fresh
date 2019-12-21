package com.hellofresh.core.driver;

import com.hellofresh.core.constants.Browsers;
import org.openqa.selenium.WebDriver;

/**
 * Class containing methods related to webdriver
 */
public class Driver {
    private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();

    public static void initialize(Browsers driverType){
        if(driverLocal.get() == null){
            driverLocal.set(new WebDriverFactory().getDriver(driverType));
        }
    }

    public static WebDriver getDriver(){
        return driverLocal.get();
    }

    public static void destroyDriver(){
        driverLocal.get().quit();
    }
}
