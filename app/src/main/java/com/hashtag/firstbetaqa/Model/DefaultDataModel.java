package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/19/17.
 */

public class DefaultDataModel {


    /**
     * status : done
     * message : تم ارسال التعليق بنجاح.
     * help : Method : POST , GET - Validation on  > newsid , userid ,content
     */

    private String status;
    private String message;

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
