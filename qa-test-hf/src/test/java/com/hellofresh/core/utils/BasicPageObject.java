package com.hellofresh.core.utils;

import com.hellofresh.core.actions.BrowserActions;
import com.hellofresh.core.actions.PageActions;
import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.driver.Driver;

public class BasicPageObject {

    private PropertyReader properties = PropertyReader.getInstance();
    protected PageActions browser_actions = new BrowserActions();

    public void openAppHomePage() {
        String url = properties.getProperty(CommonConstants.APP_URL_CONFIG);
        Driver.getDriver().get(url);
    }
}
