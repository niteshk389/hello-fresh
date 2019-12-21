package com.hellofresh.utils;

import com.hellofresh.models.BookingModel;

/**
 * Context class to maintain booking details across multiple steps
 */
public class Context {
    public BookingModel getBooking() {
        return booking;
    }

    public void setBooking(BookingModel booking) {
        this.booking = booking;
    }

    public BookingModel booking;
}
