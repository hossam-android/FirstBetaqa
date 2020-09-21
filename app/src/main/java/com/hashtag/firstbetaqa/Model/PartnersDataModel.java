package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 12/11/17.
 */

public class PartnersDataModel {


    /**
     * dalilid : 34
     * company : مجموعة المهيدب الطبية (أسنان - جلدية - ليزر )
     * name :
     * photo : https://www.firstcard.sa//uploads/thumb/upload_2017_12_09_15_11_2629.jpg
     * phone :
     * location :
     * date : 2017-12-09 15:11:00
     */

    private String dalilid;
    private String company;
    private String name;
    private String photo;
    private String phone;
    private String location;
    private String date;
    private String networkid;
private String url;

public String getNetworkid(){
    return networkid;
}
public void setNetworkid(String networkid){
    this.networkid = networkid;
}
public String getUrl(){ return url;}
public void setUrl(String url){
    this.url=url;
}
    public String getDalilid() {
        return dalilid;
    }

    public void setDalilid(String dalilid) {
        this.dalilid = dalilid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
