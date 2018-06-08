package org.launchcode.cheesemvc.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    @NotEmpty(message = "Please enter your user name")
    @Size(int min=5, int max=25)
    private String username;

    @NotEmpty(message = "Please enter your email")
    @Email
    private String email;

    @NotEmpty(message = "Please enter your password.")
    private String password;

    public User() {

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
}