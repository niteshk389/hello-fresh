package com.hellofresh.models;

import java.util.Objects;

/**
 * Booking dates POJO class
 */
public class BookingDates {
    private String checkin;
    private String checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingDates)) return false;
        BookingDates that = (BookingDates) o;
        return Objects.equals(getCheckin(), that.getCheckin()) &&
                       Objects.equals(getCheckout(), that.getCheckout());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCheckin(), getCheckout());
    }
}
