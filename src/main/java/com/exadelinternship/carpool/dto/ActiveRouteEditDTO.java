package com.exadelinternship.carpool.dto;

import java.sql.Timestamp;

public class ActiveRouteEditDTO {
    private Timestamp timeAndDate;
    private long id;

    public Timestamp getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(Timestamp timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
