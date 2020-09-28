package com.account.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class RegisteredUser extends User {

    private String userName;
    private String idType;
    private String password;

    protected RegisteredUser() {}

    public RegisteredUser(Integer idNumber, String idType, String userName, String password, String firstName, String middleName, String lastName, String phoneNumber, String country, Date dob) {
        super(idNumber, firstName, middleName, lastName, phoneNumber, country, dob);
        this.userName = userName;
        this.password = password;
        this.idType = idType;
        this.password = password;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

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
