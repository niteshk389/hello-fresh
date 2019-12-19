package com.hellofresh.models;

import com.hellofresh.constants.BookingConstants;
import com.hellofresh.constants.CommonConstants;
import com.hellofresh.utils.JSONUtils;
import com.hellofresh.utils.PropertyReader;
import com.hellofresh.utils.RestClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BookingServiceModel {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceModel.class);
    private RestClientUtils restClient = new RestClientUtils();
    private JSONUtils jsonUtils = new JSONUtils();
    PropertyReader properties = PropertyReader.getInstance();


    public final Map<String, Object> getBookingbyId(String id) {
        RequestModel request = new RequestModel(properties.getProperty("bookings.url") + BookingConstants.GET_BOOKING_BY_ID_PATH + "/" + id);
        LOGGER.info("Setting up the RequestModel to fetch booking by ID.");
        request.addHeader(CommonConstants.CONTENT_TYPE_HEADER, CommonConstants.JSON_CONTENT_TYPE);
        LOGGER.info("request params: " + request.getParams());
        LOGGER.info("Initiating the HTTP request for user user creation.");
        LOGGER.info("Making call to endpoint : " + request);
        return restClient.doGET(request, true);
    }

    public final Map<String, Object> getAllBookings() {
        RequestModel request = new RequestModel(properties.getProperty("bookings.url") + BookingConstants.GET_BOOKING_BY_ID_PATH);
        LOGGER.info("Setting up the RequestModel to fetch booking.");
        request.addHeader(CommonConstants.CONTENT_TYPE_HEADER, CommonConstants.JSON_CONTENT_TYPE);
        LOGGER.info("request params: " + request.getParams());
        LOGGER.info("Initiating the HTTP request for user user creation.");
        LOGGER.info("Making call to endpoint : " + request);
        return restClient.doGET(request, true);
    }

    public final Map<String, Object> createBooking(Map<String, String> bookingDetails) {
        RequestModel request = new RequestModel(properties.getProperty("bookings.url") + BookingConstants.GET_BOOKING_BY_ID_PATH);
        LOGGER.info("Setting up the RequestModel for fetch booking.");
        BookingModel booking = getBookingFromMap(bookingDetails);
        String jsonBody = jsonUtils.getJSONFromObject(booking);
        request.addHeader(CommonConstants.CONTENT_TYPE_HEADER, CommonConstants.JSON_CONTENT_TYPE);
        request.setRawRequest(jsonBody);
        LOGGER.info("request params: " + request.getParams());
        LOGGER.info("Initiating the HTTP request for user user creation.");
        LOGGER.info("Making call to endpoint : " + request);
        return restClient.doPOST(request, true);
    }

    public final BookingModel getBookingFromMap(Map<String, String> bookingDetails) {
        BookingModel booking = new BookingModel();
        BookingDates dates = new BookingDates();
        dates.setCheckin(bookingDetails.get(BookingConstants.CHECKIN_ATTR));
        dates.setCheckout(bookingDetails.get(BookingConstants.CHECKOUT_ATTR));
        booking.setBookingDates(dates);
        booking.setDepositpaid(Boolean.getBoolean(bookingDetails.get(BookingConstants.DEPOSITPAID_ATTR)));
        booking.setEmail(bookingDetails.get(BookingConstants.EMAIL_ATTR));
        booking.setFirstname(bookingDetails.get(BookingConstants.FIRSTNAME_ATTR));
        booking.setLastname(bookingDetails.get(BookingConstants.LASTNAME_ATTR));
        booking.setPhone(bookingDetails.get(BookingConstants.PHONE_ATTR));
        booking.setRoomid(Integer.parseInt(bookingDetails.get(BookingConstants.ROOMID_ATTR)));
        return booking;
    }
}
