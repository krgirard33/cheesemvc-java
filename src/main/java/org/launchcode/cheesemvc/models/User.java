package org.launchcode.cheesemvc.models;

import org.launchcode.cheesemvc.controllers.UserController;

public class User {
    private String username;
    private String email;
    private String password;
    private int userId;
    private static int nextUserId = 1;

    public User(String username, String email) {
        this();
        this.username = username;
        this.email = email;
    }
    public User() {
        userId = nextUserId;
        nextUserId++;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
