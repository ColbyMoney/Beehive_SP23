package com.example.ProjectBeehive.security;

public class UserResponse {
    private String id;
    private String email;
    private String fName;
    private String lName;
    private String username;

    // Constructor (empty)
    public UserResponse() {
    }

    // Constructor with all fields
    public UserResponse(String id, String email, String fName, String lName, String username) {
        this.id = id;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

