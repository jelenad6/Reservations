package com.mycompany.data;

import java.io.Serializable;

public class Reservation implements Serializable {

    private int id;
    private int userId;
    private int slotId;
    private String status;     // ACTIVE, CANCELED
    private Integer seriesId;
     

    public Reservation() {}

    public Reservation(int id, int userId, int slotId, String status, Integer seriesId) {
        this.id = id;
        this.userId = userId;
        this.slotId = slotId;
        this.status = status;
        this.seriesId = seriesId;
       
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Integer getSeriesId() { return seriesId;  }
    public void setSeriesId(Integer seriesId) {  this.seriesId = seriesId; }
        
}
