package com.mycompany.data;

import java.io.Serializable;

public class ReservationSeries implements Serializable {

    private int id;
    private String type;      // daily, weekly, monthly

    // datumi kao String (ISO: YYYY-MM-DD)
    private String startDate;
    private String endDate;

    // podaci za reservation
    private int userId;
    private int slotId;

    public ReservationSeries() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
