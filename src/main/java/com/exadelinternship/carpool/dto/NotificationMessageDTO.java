package com.exadelinternship.carpool.dto;

import java.util.List;

public class NotificationMessageDTO {
    private long[] bookingId;
    private String information;

    public long[] getBookingId() {
        return bookingId;
    }

    public void setBookingId(long[] bookingId) {
        this.bookingId = bookingId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
