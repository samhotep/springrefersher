package com.account.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdminForm {

    @NotNull
    @Size(min = 2, message = "Invalid username")
    private String userName;

    @NotNull
    @Size(min = 8, message = "Password too short")
    private String password;

    @NotNull
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
