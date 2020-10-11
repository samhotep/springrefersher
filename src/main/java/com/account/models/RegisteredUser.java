package com.account.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Random;

@Entity
public class RegisteredUser extends IPRSUser implements User {

    private String userName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String token;
    private String role;

    protected RegisteredUser() {}

    public RegisteredUser(Integer idNumber, String idType, String userName, String password, String firstName, String middleName, String lastName, String phoneNumber, String country, Date dob) {
        super(idNumber, firstName, middleName, lastName, phoneNumber, country, idType, dob);
        this.userName = userName;
        this.password = password;
        this.token = generateToken();
        this.role = "User";
    }

    private String generateToken(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 64;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
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
