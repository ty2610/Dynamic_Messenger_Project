package com.company;

public class Company {
    private long id;
    private String companyName;
    private String cityName;
    private String timeZone;

    public Company(long id, String companyName, String cityName, String timeZone) {
        this.id = id;
        this.companyName = companyName;
        this.cityName = cityName;
        this.timeZone = timeZone;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
