package com.sj.springboot.dto;

public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T response;

    public ApiResponse() {
    }

    public ApiResponse(int statusCode, String message, T response) {
        this.statusCode = statusCode;
        this.message = message;
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }
}
