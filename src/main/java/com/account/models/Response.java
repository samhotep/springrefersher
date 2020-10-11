package com.account.models;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Set;

public class Response {

    private Integer code;
    private String message;
    private String description;
    private List<ObjectError> error;
    private IPRSUser data;
    private List<RegisteredUser> registeredUsers;
    private List<Administrator> administrators;
    private Set<Permissions> permissions;

    public Response(Integer code, String message, String description){
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public Response(Integer code, String message, IPRSUser data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(Integer code, String message, Integer methodNumber, List<RegisteredUser> registeredUsers){
        this.code = code;
        this.message = message;
        this.registeredUsers = registeredUsers;
    }

    public Response(Integer code, String message, Set<Permissions> permissions){
        this.code = code;
        this.message = message;
        this.permissions = permissions;
    }

    public Response(Integer code, String message, List<Administrator> administrators){
        this.code = code;
        this.message = message;
        this.administrators = administrators;
    }

    public Response(Integer code, String message, String description, List<ObjectError> error){
        this.code = code;
        this.message = message;
        this.description = description;
        this.error = error;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<Administrator> administrators) {
        this.administrators = administrators;
    }

    public List<RegisteredUser> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(List<RegisteredUser> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ObjectError> getError() { return error; }

    public void setError(List<ObjectError> error) { this.error = error; }

    public IPRSUser getData() {
        return data;
    }

    public void setData(IPRSUser data) {
        this.data = data;
    }

}
