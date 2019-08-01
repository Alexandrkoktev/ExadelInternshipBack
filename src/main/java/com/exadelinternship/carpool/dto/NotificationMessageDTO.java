package com.exadelinternship.carpool.dto;

import java.util.List;

public class NotificationMessageDTO {
    private List<Long> bookingId;
    private String information;

    public List<Long> getBookingId() {
        return bookingId;
    }

    public void setBookingId(List<Long> bookingId) {
        this.bookingId = bookingId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
