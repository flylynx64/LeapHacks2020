package com.example.leaphacks;

public class Event {
    private String startTime;
    private String endTime;
    private String name;
    private String location;
    private String description;

    public Event() {

    }
    public Event(final String startTime, String endTime, String name, String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.location = location;
    }

    public Event(final String startTime, String endTime, String name, String location, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public String getStartTime () { return startTime; }
    public String getEndTime () { return endTime; }
    public String getName () { return name; }
    public String getLocation () { return location; }
    public String getDescription () { return description; }
    
}
