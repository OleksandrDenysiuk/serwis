package com.example.travelInfo;

public class Test {
    private String id;
    private String lat;
    private String lng;
    private String address;

    public Test(){

    }

    public Test(String id, String lat, String lng, String address) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
