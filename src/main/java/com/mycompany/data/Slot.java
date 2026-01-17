package com.mycompany.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Slot implements Serializable {

    private int id;
    private int resourceId;
    private LocalDate date;
    private LocalTime time;

    public Slot() {}

    public Slot(int id, int resourceId, LocalDate date, LocalTime time) {
        this.id = id;
        this.resourceId = resourceId;
        this.date = date;
        this.time = time;
    }

    public Slot(int resourceId, LocalDate date, LocalTime time) {
        this.resourceId = resourceId;
        this.date = date;
        this.time = time;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getResourceId() { return resourceId; }
    public void setResourceId(int resourceId) { this.resourceId = resourceId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
}
