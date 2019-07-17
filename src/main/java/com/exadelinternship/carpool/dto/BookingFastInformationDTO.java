package com.exadelinternship.carpool.dto;

import java.sql.Timestamp;

public class BookingFastInformationDTO {
    private long id;
    private Timestamp timeAndDate;
    private String startPointName;
    private String finishPointName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(Timestamp timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getStartPointName() {
        return startPointName;
    }

    public void setStartPointName(String startPointName) {
        this.startPointName = startPointName;
    }

    public String getFinishPointName() {
        return finishPointName;
    }

    public void setFinishPointName(String finishPointName) {
        this.finishPointName = finishPointName;
    }
}
