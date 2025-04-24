package com.example.userManagement.dto;


import lombok.Data;

//client to server
public class UserLocationDTO {
    private Long userid;
    private String email;
    private String place;
    private double longitude;
    private double latitude;

    public UserLocationDTO() {
    }

    public UserLocationDTO(Long userid, String email, String place, double longitude, double latitude) {
        this.userid = userid;
        this.email = email;
        this.place = place;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
