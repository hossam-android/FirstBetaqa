package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/17/17.
 */

public class MainCatsDataModel {


    /**
     * catid : 1
     * title : الشبكة الطبية
     * photo : https://www.firstcard.sa/uploads/thumb/upload_2017_10_07_16_58_12123.jpg
     * type : cats
     * url : https://www.firstcard.sa/api.php?mod=regions&cid=1
     * subcategoriesCount : 0
     * subcategories :
     * id : 7
     * lastupdate : none
     * totalitems : 2
     */

    private String catid;
    private String title;
    private String photo;
    private String type;
    private String url;
    private int subcategoriesCount;
    private String subcategories;
    private String id;

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSubcategoriesCount() {
        return subcategoriesCount;
    }

    public void setSubcategoriesCount(int subcategoriesCount) {
        this.subcategoriesCount = subcategoriesCount;
    }

    public String getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(String subcategories) {
        this.subcategories = subcategories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
