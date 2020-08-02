package com.example.starvelater.jsonmodels;

public class UserRegistrationModel {

    /**
     * status : true
     * msg : Record has been added successfully
     */

    private boolean status;
    private String msg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
