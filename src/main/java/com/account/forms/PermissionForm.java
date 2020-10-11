package com.account.forms;

import com.account.models.Administrator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class PermissionForm {

    @NotNull
    private String user;

    @NotNull
    private String permissionName;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
