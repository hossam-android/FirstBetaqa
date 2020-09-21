package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/18/17.
 */

public class SubCatsDataModel {


    /**
     * id : 1
     * name : المنطقة الوسطى
     * photo : https://www.firstcard.sa/beta/uploads/thumb/upload_2017_08_22_16_14_08195.jpg
     * total : 1
     * catid : 1
     * url : https://www.firstcard.sa/beta/api.php?mod=cities2&cid=1&region=1
     */

    private String id;
    private String name;
    private String photo;
    private int total;
    private int catid;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
