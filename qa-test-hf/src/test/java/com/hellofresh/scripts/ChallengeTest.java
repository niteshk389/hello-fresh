package com.hellofresh.scripts;

import com.hellofresh.core.constants.CommonConstants;
import com.hellofresh.core.listeners.ExtentTestManager;
import com.hellofresh.core.testdata.JSONDataRetriever;
import com.hellofresh.core.testdata.TestDataGenerator;
import com.hellofresh.core.utils.BasicTest;
import com.hellofresh.pageobjects.AddressDetailsPage;
import com.hellofresh.pageobjects.CartSummaryPage;
import com.hellofresh.pageobjects.ConfirmYourOrderPage;
import com.hellofresh.pageobjects.HomePage;
import com.hellofresh.pageobjects.OrderConfirmationPage;
import com.hellofresh.pageobjects.PaymentDetailsPage;
import com.hellofresh.pageobjects.ProductDetailsPage;
import com.hellofresh.pageobjects.ProductListPage;
import com.hellofresh.pageobjects.RegistrationPage;
import com.hellofresh.pageobjects.ShippingDetailsPage;
import com.hellofresh.pageobjects.SignInLandingPage;
import com.hellofresh.pageobjects.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class ChallengeTest extends BasicTest {

    String loginDataFileName = CommonConstants.RESOURCE_DIR + "LoginData.json";
    String registrationDataFileName = CommonConstants.RESOURCE_DIR + "RegistrationData.json";
    Map<String, String> loginData = new JSONDataRetriever().getDataObject(loginDataFileName);
    Map<String, String> registrationData = new JSONDataRetriever().getDataObject(registrationDataFileName);

    @Test
    public void registrationTest(Method method) {
        ExtentTestManager.startTest(method.getName(), "Test for registration functionality.");
        HomePage homePage = new HomePage();
        homePage.openAppHomePage();
        SignInPage signinPage = homePage.clickLoginLink();
        signinPage.enterRegistrationEmail(new TestDataGenerator().randomAlphabeticForMail());
        RegistrationPage registrationPage = signinPage.clickCreateAccountButton();
        registrationPage.enterRegistrationDetails(registrationData);
        SignInLandingPage landingPage = registrationPage.clickSubmitAccountButton();

        Assert.assertEquals(landingPage.getHeadingText(), "MY ACCOUNT");
        Assert.assertEquals(landingPage.getAccountLabelText(), registrationData.get("firstname") + " " + registrationData.get("lastname") );
        Assert.assertTrue(landingPage.getAccountInfoLabelText().contains("Welcome to your account."));
        Assert.assertTrue(landingPage.isLogoutVisible());
        Assert.assertTrue(landingPage.getCurrentUrl().contains("controller=my-account"));
    }

    @Test
    public void signInTest(Method method) {
        ExtentTestManager.startTest(method.getName(), "Test for Signin functionality.");
        HomePage homePage = new HomePage();
        homePage.openAppHomePage();
        SignInPage signinPage = homePage.clickLoginLink();
        SignInLandingPage landingPage = signinPage.signIn(loginData);

        Assert.assertEquals(landingPage.getHeadingText(), "MY ACCOUNT");
        Assert.assertEquals(landingPage.getAccountLabelText(), loginData.get("fullname"));
        Assert.assertTrue(landingPage.getAccountInfoLabelText().contains("Welcome to your account."));
        Assert.assertTrue(landingPage.isLogoutVisible());
        Assert.assertTrue(landingPage.getCurrentUrl().contains("controller=my-account"));
    }

    @Test
    public void checkoutTest(Method method) {
        ExtentTestManager.startTest(method.getName(), "Test for checkout functionality.");
        HomePage homePage = new HomePage();
        homePage.openAppHomePage();
        SignInPage signinPage = homePage.clickLoginLink();
        SignInLandingPage landingPage = signinPage.signIn(loginData);
        ProductListPage productListPage = landingPage.clickwomenTabLink();
        ProductDetailsPage detailsPage = productListPage.clickTshirtLink();
        detailsPage.clickAddToCartButton();
        CartSummaryPage cartSummaryPage = detailsPage.clickProceedToCheckoutButton();
        AddressDetailsPage addressDetailsPage = cartSummaryPage.clickProceedToCheckOutButton();
        ShippingDetailsPage shippingDetailsPage = addressDetailsPage.clickProceedToCheckoutButton();
        shippingDetailsPage.checkTermsAndConditionsCheckBox();
        PaymentDetailsPage paymentDetailsPage = shippingDetailsPage.clickProceedToCheckoutButton();
        ConfirmYourOrderPage confirmYourOrderPage = paymentDetailsPage.clickBankWirePaymentLink();
        OrderConfirmationPage orderConfirmationPage = confirmYourOrderPage.clickConfirmOrderButton();

        Assert.assertEquals("ORDER CONFIRMATION", orderConfirmationPage.getHeadingText());
        Assert.assertTrue(orderConfirmationPage.isShipingChevronDisplayed());
        Assert.assertTrue(orderConfirmationPage.isPaymentChevronDisplayed());
        Assert.assertTrue(orderConfirmationPage.getOrderConfirmationText().contains("Your order on My Store is complete."));
        Assert.assertTrue(orderConfirmationPage.getCurrentUrl().contains("controller=order-confirmation"));



    }
}
