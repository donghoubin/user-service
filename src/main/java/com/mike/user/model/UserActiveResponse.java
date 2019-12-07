package com.mike.user.model;

/**
 * @Description:
 * @Author: Mike Dong
 * @Date: 2019/12/4 17:51.
 */
public class UserActiveResponse {
    private String responseState;

    private String message;

    public String getResponseState() {
        return responseState;
    }

    public void setResponseState(String responseState) {
        this.responseState = responseState;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
