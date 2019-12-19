package com.hellofresh.steps;

import com.hellofresh.constants.CommonConstants;
import com.hellofresh.models.BookingServiceModel;
import com.hellofresh.utils.JSONUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingServiceSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceSteps.class);
    private BookingServiceModel bookingModel = new BookingServiceModel();
    private Map<String, Object> response = new HashMap<String, Object>();
    private JSONUtils jsonUtil = new JSONUtils();

    @Given("^I make request to fetch all bookings$")
    public final void iFetchBookingDetals() throws Throwable {
        response.clear();
        response = bookingModel.getAllBookings();
    }

    @Given("^I create a bookings with below details$")
    public final void iCreateABooking(Map<String, String> bookingDataTable) throws Throwable {
        response.clear();
        response = bookingModel.createBooking(bookingDataTable);
    }

    @Then("^I get repsponse code as \"(.*?)\"$")
    public final void iGetResponseAs(int responseCode) throws Throwable {
        Assert.assertTrue(((int) response.get(CommonConstants.HTTP_STATUS_CODE)) == responseCode, "Bookings are less than 1.");
    }

    @Then("^I get atleast 1 booking in response$")
    public final void iGetAtleastOneBooking() throws Throwable {
        Map<String, Object> responseBody = jsonUtil.getJSONObjectFromString((String) response.get(CommonConstants.HTTP_RESPONSE_BODY), Map.class);
        List<Object> bookings = (ArrayList<Object>)responseBody.get("bookings");
        Assert.assertTrue( bookings.size() > 1, "Bookings are less than 1.");
    }
}
