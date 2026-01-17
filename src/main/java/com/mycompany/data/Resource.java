package com.mycompany.data;

import java.io.Serializable;

public class Resource implements Serializable {

    private int id;
    private String name;
    private String type;
    private String workingHours;

    public Resource() {}

    public Resource(int id, String name, String type, String workingHours) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.workingHours = workingHours;
    }

    public Resource(String name, String type, String workingHours) {
        this.name = name;
        this.type = type;
        this.workingHours = workingHours;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getWorkingHours() { return workingHours; }
    public void setWorkingHours(String workingHours) { this.workingHours = workingHours; }
}
