package com.aad.cn47.gadsleaderboard2.models;

public class Hour {
    private int id;
    private String name;
    private String hours;
    private String country;

    public Hour() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
