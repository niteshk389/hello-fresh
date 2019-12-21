package com.hellofresh.models;

import com.hellofresh.constants.BookingConstants;
import com.hellofresh.constants.CommonConstants;
import com.hellofresh.utils.JSONUtils;
import com.hellofresh.utils.PropertyReader;
import com.hellofresh.utils.RestClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;

public class BookingServiceModel {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceModel.class);
    private RestClientUtils restClient = new RestClientUtils();
    private JSONUtils jsonUtils = new JSONUtils();
    PropertyReader properties = PropertyReader.getInstance();


    public final Map<String, Object> getBookingbyId(int id) {
        //create request model
        RequestModel request = new RequestModel(properties.getProperty("bookings.url") + BookingConstants.GET_BOOKING_BY_ID_PATH + id);
        LOGGER.info("Setting up the RequestModel to fetch booking by ID.");
        request.addHeader(CommonConstants.CONTENT_TYPE_HEADER, CommonConstants.JSON_CONTENT_TYPE);
        LOGGER.info("request params: " + request.getParams());
        LOGGER.info("Initiating the HTTP request for user user creation.");
        LOGGER.info("Making call to endpoint : " + request);

        //make get request to get booking by ID
        return restClient.doGET(request, true);
    }

    public final Map<String, Object> getAllBookings() {
        //create request model
        RequestModel request = new RequestModel(properties.getProperty("bookings.url") + BookingConstants.GET_BOOKING_BY_ID_PATH);
        LOGGER.info("Setting up the RequestModel to fetch booking.");
        request.addHeader(CommonConstants.CONTENT_TYPE_HEADER, CommonConstants.JSON_CONTENT_TYPE);
        LOGGER.info("request params: " + request.getParams());
        LOGGER.info("Initiating the HTTP request for user user creation.");
        LOGGER.info("Making call to endpoint : " + request);

        //make get request to get all booking
        return restClient.doGET(request, true);
    }

    public final Map<String, Object> createBooking(BookingModel booking) {
        //create request model
        RequestModel request = new RequestModel(properties.getProperty("bookings.url") + BookingConstants.CREATE_BOOKING_PATH);
        LOGGER.info("Setting up the RequestModel for fetch booking.");
        String jsonBody = jsonUtils.getJSONFromObject(booking);
        request.addHeader(CommonConstants.CONTENT_TYPE_HEADER, CommonConstants.JSON_CONTENT_TYPE);
        request.setRawRequest(jsonBody);
        LOGGER.info("request params: " + request.getParams());
        LOGGER.info("Initiating the HTTP request for user user creation.");
        LOGGER.info("Making call to endpoint : " + request);

        //make post request to create booking
        return restClient.doPOST(request, true);
    }

    public final BookingModel getBookingFromMap(Map<String, String> bookingDetails) {

        //create booking object from map<string, string>
        BookingModel booking = new BookingModel();
        BookingDates dates = new BookingDates();
        dates.setCheckin(bookingDetails.get(BookingConstants.CHECKIN_ATTR));
        dates.setCheckout(bookingDetails.get(BookingConstants.CHECKOUT_ATTR));
        booking.setBookingDates(dates);
        if (bookingDetails.get(BookingConstants.BOOKINGID_ATTR) != null) {
            booking.setBookingid(Integer.parseInt(bookingDetails.get(BookingConstants.BOOKINGID_ATTR)));
        }
        booking.setDepositpaid(Boolean.getBoolean(bookingDetails.get(BookingConstants.DEPOSITPAID_ATTR)));
        booking.setEmail(bookingDetails.get(BookingConstants.EMAIL_ATTR));
        booking.setFirstname(bookingDetails.get(BookingConstants.FIRSTNAME_ATTR));
        booking.setLastname(bookingDetails.get(BookingConstants.LASTNAME_ATTR));
        booking.setPhone(bookingDetails.get(BookingConstants.PHONE_ATTR));
        if (bookingDetails.get(BookingConstants.ROOMID_ATTR).equalsIgnoreCase("random")) {
            Random random = new Random();
            booking.setRoomid(random.nextInt(10000));
        } else {
            booking.setRoomid(Integer.parseInt(bookingDetails.get(BookingConstants.ROOMID_ATTR)));
        }
        return booking;
    }
}
