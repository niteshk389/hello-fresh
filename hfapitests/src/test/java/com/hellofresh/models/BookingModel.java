package com.hellofresh.models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Objects;

/**
 * Booking POJO class
 */
public class BookingModel {

    @JsonProperty("bookingdates")
    private BookingDates bookingdates;

    @JsonProperty("bookingid")
    private int bookingid;

    @JsonProperty("depositpaid")
    private Boolean depositpaid;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("roomid")
    private int roomid;

    public BookingDates getBookingDates() {
        return bookingdates;
    }

    public void setBookingDates(BookingDates bookingDates) {
        this.bookingdates = bookingDates;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingModel)) return false;
        BookingModel that = (BookingModel) o;
        return getRoomid() == that.getRoomid() &&
                       Objects.equals(getBookingDates(), that.getBookingDates()) &&
                       Objects.equals(getBookingid(), that.getBookingid()) &&
                       Objects.equals(getDepositpaid(), that.getDepositpaid()) &&
                       Objects.equals(getEmail(), that.getEmail()) &&
                       Objects.equals(getFirstname(), that.getFirstname()) &&
                       Objects.equals(getLastname(), that.getLastname()) &&
                       Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookingDates(), getBookingid(), getDepositpaid(), getEmail(), getFirstname(), getLastname(), getPhone(), getRoomid());
    }
}
