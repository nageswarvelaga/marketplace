package com.app.marketplace.model;

public class ErrorDTO {

    String status;

    String message;

    public ErrorDTO(String status, String msg) {
        this.status = status;
        message = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
