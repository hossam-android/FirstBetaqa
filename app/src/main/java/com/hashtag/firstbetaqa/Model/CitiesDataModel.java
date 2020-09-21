package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 12/10/17.
 */

public class CitiesDataModel {


    /**
     * cityid : 1
     * cityname : المنطقة الوسطى
     * photo : https://www.firstcard.sa//uploads/thumb/upload_2017_10_07_16_38_14128.jpg
     * url : https://www.firstcard.sa/api.php?mod=district&city=1
     */

    private String cityid;
    private String cityname;
    private String photo;
    private String url;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
