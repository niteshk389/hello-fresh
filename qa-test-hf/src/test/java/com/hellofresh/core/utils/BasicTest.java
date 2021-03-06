package com.hellofresh.core.utils;

import com.hellofresh.core.constants.Browsers;
import com.hellofresh.core.driver.Driver;
import com.hellofresh.core.listeners.ExtentManager;
import com.hellofresh.core.listeners.ExtentTestManager;
import com.hellofresh.core.listeners.StepLogger;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

/**
 * Class to be extended by all test scripts - contains all setup and teardown methods
 */
public class BasicTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicTest.class);

    @BeforeClass
    public static void setUp(){

    }

    @AfterMethod
    public void cleanUp(){

    }

    @AfterClass
    public static void tearDown(){
    }

    @BeforeMethod
    public void setup() {
        String browser = System.getProperty("browser");
        if(browser == null) {
            Driver.initialize(Browsers.CHROME_BROWSER);
        } else {
            Driver.initialize(Browsers.fromString(browser.toLowerCase()));
        }
        Driver.getDriver().manage().deleteAllCookies();
        StepLogger.clearMethodsCallsList();
    }

    @AfterMethod
    public void logEnd() {
        LOGGER.info("================================================================");
        LOGGER.info("Start of Test case");
        LOGGER.info("================================================================");
        //Get all intercepted @Step annotation calls
        for(String message : StepLogger.getMethodsCallsList()) {
            LOGGER.info(message);
            ExtentTestManager.getTest().log(LogStatus.PASS, message);
        }
        LOGGER.info("================================================================");
        LOGGER.info("End of Test case");
        LOGGER.info("================================================================");
        Driver.destroyDriver();
    }
}
