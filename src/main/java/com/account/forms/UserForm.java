package com.account.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    @NotNull(message = "Please enter a value")
    @Size(min = 2, max = 20, message = "Too short")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Must not contain special characters")
    private String userName;

    @Pattern(regexp = "[a-zA-Z]*", message = "Must only contain letters")
    private String firstName;

    @Pattern(regexp = "[a-zA-Z]*", message = "Must only contain letters")
    private String middleName;

    @Pattern(regexp = "[a-zA-Z]*", message = "Must only contain letters")
    private String lastName;

    @NotNull(message = "Please enter a value")
    @Size(min = 8, message = "Password too short")
    private String password;

    @NotNull(message = "Please enter a value")
    @Size(min = 4, max = 15, message = "Please verify the number length")
    @Pattern(regexp = "[0-9]*", message = "Must only contain numbers")
    private String idNumber;

    @NotNull(message = "Please enter a value")
    @Pattern(regexp = "[a-zA-Z]+", message = "Please enter a valid country")
    private String country;

    @NotNull(message = "Please enter a value")
    @Pattern(regexp = "^(07|2547)[0-9]{8}", message = "Please enter a valid phone number")
    private String phoneNumber;

    @NotNull(message = "Please enter a value")
    @Pattern(regexp = "[a-zA-Z]*", message = "Please enter a valid id type")
    private String idType;

    @NotNull(message = "Please enter a value")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "Please enter a valid date")
    private String dob;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
