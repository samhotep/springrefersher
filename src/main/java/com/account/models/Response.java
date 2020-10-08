package com.account.models;

import org.springframework.validation.ObjectError;

import java.util.List;

public class Response {

    private Integer code;
    private String message;
    private String description;
    private List<ObjectError> error;
    private IPRSUser data;

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

    public Response(Integer code, String message, String description, List<ObjectError> error){
        this.code = code;
        this.message = message;
        this.description = description;
        this.error = error;
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
