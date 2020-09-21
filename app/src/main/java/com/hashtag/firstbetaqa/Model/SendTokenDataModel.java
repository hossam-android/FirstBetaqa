package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 8/27/17.
 */

public class SendTokenDataModel {


    /**
     * deviceid : null
     * status : done
     * message : تم تحديث الجهاز بنجاح.
     */

    private String deviceid;
    private String status;
    private String message;


    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
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
