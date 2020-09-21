package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/18/17.
 */

public class SubRegionDataModel {

    /**
     * catName : الشبكة الطبية
     * catid : 1
     * itemid : 11
     * title : مركز التميز في علوم الجينوم الطبي
     * name : مركز التميز في علوم الجينوم الطبي
     * photo : https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_11_23_2787.png
     * lastUpdate : 2017-09-18 11:23:00
     * description : حصل على خصم فوري يصل الى 20% عند استخدامك البطاقة الاولي كارد (الخصم غير ساري على أي ...
     * share : مركز التميز في علوم الجينوم الطبي
     حصل على خصم فوري يصل الى 20% عند استخدامك البطاقة الاولي كارد (الخصم غير ساري على أي تحاليل تتطلب السفر ...
     تم النشر عن طريق تطبيق تطبيق البطاقة الاولى
     * views : 2
     * content : <p>حصل على خصم فوري يصل الى 20% عند استخدامك البطاقة الاولي كارد (الخصم غير ساري على أي تحاليل تتطلب السفر للخارج/أو أي تحاليل يتم عملها خارج مصر)</p>
     * url : https://www.firstcard.sa/beta/api.php?mod=market&itemid=11
     */

    private String catName;
    private String catid;
    private String itemid;
    private String title;
    private String name;
    private String photo;
    private String lastUpdate;
    private String description;
    private String share;
    private int views;
    private String content;
    private String url;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
