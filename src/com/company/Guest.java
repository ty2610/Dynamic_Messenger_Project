package com.company;

import java.sql.Timestamp;
import java.util.Date;

public class Guest {
    private long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private long roomNumber;
    private Date startTimestamp;
    private Date endTimestamp;

    public Guest(long id, String firstName, String lastName, String fullName, long roomNumber, Date startTimestamp, Date endTimestamp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.roomNumber = roomNumber;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Date endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
