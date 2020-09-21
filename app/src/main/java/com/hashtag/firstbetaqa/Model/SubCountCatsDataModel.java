package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 12/12/17.
 */

public class SubCountCatsDataModel {


    /**
     * catid : 42
     * catName : تجربة 1
     * photo : https://www.firstcard.sa/uploads/thumb/upload_2017_10_07_16_58_12132.jpg
     * totalitems : 0
     * url : https://www.firstcard.sa/api.php?mod=regions&cid=42
     * subcategoriestotal : 0
     * subcategories :
     */

    private String catid;
    private String catName;
    private String photo;
    private int totalitems;
    private String url;

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(int totalitems) {
        this.totalitems = totalitems;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
