package com.hellofresh.core.driver;

import com.hellofresh.core.constants.Browsers;
import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Factory pattern class to get webdriver instances based on browser
 */
public class WebDriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);
    PropertyReader properties = PropertyReader.getInstance();

    public WebDriver getDriver(Browsers browser) {
        WebDriver driver;
        //case statement to get browser based on input browser name
        if (browser != null ){
            switch (browser){
                case CHROME_BROWSER:
                    driver =  getChromeDriver();
                    break;
                case FIREFOX_BROWSER :
                    driver =  getFirefoxDriver();
                    break;
                case SAFARI_BROWSER :
                    driver =  getSafariDriver();
                    break;
                default:
                    LOGGER.error("Unrecognized Browser. Please provide valid browser");
                    driver =  null;
            }
        }
        else{
            //default browser
            driver =  getFirefoxDriver();
        }
        //default implicit wait - 10s
        int implicitwait = 10;
        if(properties.getProperty(CommonConstants.IMPLICIT_WAIT_SETTING) != null) {
            implicitwait = Integer.parseInt(properties.getProperty(CommonConstants.IMPLICIT_WAIT_SETTING));
        }
        //specify implicit wait for all the elements
        driver.manage().timeouts().implicitlyWait(implicitwait, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver getSafariDriver() {
        System.setProperty(CommonConstants.SAFARI_DRIVER_PATH_CONFIG, System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        return new SafariDriver();
    }

    private WebDriver getFirefoxDriver() {
        System.setProperty(CommonConstants.GECKO_DRIVER_PATH_CONFIG, System.getProperty("user.dir") + "/src/test/resources/geckodriver");
        return new FirefoxDriver();
    }

    private WebDriver getChromeDriver() {
        System.setProperty(CommonConstants.CHROME_DRIVER_PATH_CONFIG, System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

}
