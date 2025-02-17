package com.paintingscollectors.model.dto;

import com.paintingscollectors.vallidation.annotation.UniqueEmail;
import com.paintingscollectors.vallidation.annotation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterDTO {
    @NotNull(message = "Username is required.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    @UniqueUsername
    private String username;

    @NotNull(message = "Password is required.")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
    private String password;

    @NotNull(message = "Email is required.")
    @Email(message = "Email must be valid.")
    @UniqueEmail
    private String email;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}