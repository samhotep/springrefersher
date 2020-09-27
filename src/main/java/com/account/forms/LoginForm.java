package com.account.forms;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

    @NotNull
    @Size(min = 2, max = 20, message = "Invalid username")
    private String userName;

    @NotNull
    @Size(min = 8, max = 20, message = "")
    private String password;

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
}
