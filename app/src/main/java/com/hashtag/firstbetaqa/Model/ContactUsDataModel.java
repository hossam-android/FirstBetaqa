package com.hashtag.firstbetaqa.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ebda3-mint on 9/19/17.
 */

public class ContactUsDataModel {


    /**
     * title : البطاقة الاولى
     * address : 2 شارع جسر السويس / عمارات الميرلاند 2212212
     * lat : 24.0133137
     * long : 40.6050726
     * site : null
     * email : sdfsfds@dsf.dsfds
     * phone : 888
     * youtube : youtube
     * twitter : twitter
     * instagram : instagram
     * facebook : facebook
     * facebookID : facebookID
     * skype : skype
     * googleplus : googleplus
     */

    private String title;
    private String address;
    private String lat;
    @SerializedName("long")
    private String longX;
    private Object site;
    private String email;
    private String phone;
    private String youtube;
    private String twitter;
    private String instagram;
    private String facebook;
    private String facebookID;
    private String skype;
    private String googleplus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongX() {
        return longX;
    }

    public void setLongX(String longX) {
        this.longX = longX;
    }

    public Object getSite() {
        return site;
    }

    public void setSite(Object site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getGoogleplus() {
        return googleplus;
    }

    public void setGoogleplus(String googleplus) {
        this.googleplus = googleplus;
    }
}
