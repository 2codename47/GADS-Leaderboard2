package com.aad.cn47.gadsleaderboard2.models;

public class Skilliq {
    private int id;
    private String name;
    private String score;
    private String country;

    public Skilliq() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
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

    public void setScore(String score) {
        this.score = score;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
