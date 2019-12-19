package com.hellofresh.scripts;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.listeners.ExtentTestManager;
import com.hellofresh.core.listeners.StepLogger;
import com.hellofresh.core.testdata.DataRetriever;
import com.hellofresh.core.testdata.ExcelDataRetriever;
import com.hellofresh.core.testdata.JSONDataRetriever;
import com.hellofresh.core.utils.BasicTest;
import com.hellofresh.pageobjects.HomePage;
import com.hellofresh.pageobjects.RegistrationPage;
import com.hellofresh.pageobjects.SignInPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Map;

@Listeners({com.hellofresh.core.listeners.CustomListener.class})
public class ChallengeTest extends BasicTest {

    String loginDataFileName = CommonConstants.RESUORCE_DIR + "LoginData.json";
    String registrationDataFileName = CommonConstants.RESUORCE_DIR + "RegistrationData.json";
    Map<String, String> loginData = new JSONDataRetriever().getDataObject(loginDataFileName);
    Map<String, String> registrationData = new JSONDataRetriever().getDataObject(registrationDataFileName);

//    @Test
//    public void registrationTest(Method method) {
//    ExtentTestManager.startTest(method.getName(), "Test for registration functionality.");
//        HomePage homePage = new HomePage();
//        homePage.openAppHomePage();
//        SignInPage signinPage = homePage.clickLoginLink();
//        signinPage.enterRegistrationEmail(loginData.get("email"));
//        RegistrationPage registrationPage = signinPage.clickCreateAccountButton();
//        registrationPage.enterRegistrationDetails(registrationData);
//        registrationPage.clickSubmitAccountButton();
//    }

    @Test
    public void signInTest(Method method) {
        ExtentTestManager.startTest(method.getName(), "Test for Signin functionality.");
        HomePage homePage = new HomePage();
        homePage.openAppHomePage();
        SignInPage signinPage = homePage.clickLoginLink();
        signinPage.enterEmailAddress(loginData.get("email"));
        signinPage.enterPassword(loginData.get("password"));
        signinPage.clickSignInButton();
    }

    @Test
    public void checkoutTest(Method method) {
        ExtentTestManager.startTest(method.getName(), "Test for checkout functionality.");
        HomePage homePage = new HomePage();
        homePage.openAppHomePage();
        SignInPage signinPage = homePage.clickLoginLink();
        signinPage.enterEmailAddress(loginData.get("email"));
        signinPage.enterPassword(loginData.get("password"));
        signinPage.clickSignInButton();
    }
}
