package com.aad.cn47.gadsleaderboard2.models;

public class SubmitProject {
    private String first_name;
    private String last_name;
    private String email;
    private String project_link;

    public SubmitProject() {
    }

    // Getters

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getProjectLink() {
        return project_link;
    }

    // Setters

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProjectLink(String project_link) {
        this.project_link = project_link;
    }
}
