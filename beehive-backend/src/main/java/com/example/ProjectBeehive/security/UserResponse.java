package com.example.ProjectBeehive.security;

public class UserResponse {
    private String fName;
    private String lName;
    private String username;

    // Constructor (empty)
    public UserResponse() {
    }

    // Constructor with all fields
    public UserResponse(String fName, String lName, String username) {
        this.fName = fName;
        this.lName = lName;
        this.username = username;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

