package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/18/17.
 */

public class NewsDataModel {


    /**
     * catId : 92
     * catName : الأخبار
     * newsid : 13
     * title : تليفونات سامسونج
     * photo : https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_49_5472.jpg
     * lastUpdate :  منذ 5 ساعة
     * ar_date : {"nameday":"الاثنين","day":"18","namemonth":"سبتمبر","year":"2017"}
     * date : 18-09-2017
     * time : 10:49 AM
     * description : انضمام تليفونات سامسونج الى البطاقة الاولي كارد
     * share : تليفونات سامسونج
     انضمام تليفونات سامسونج الى البطاقة الاولي كارد
     تم النشر عن طريق تطبيق تطبيق البطاقة الاولى
     * views : 1
     * numberOfComments : 14
     * numberOfLikes : 0
     * numberOfDislikes : 0
     * userId : 0
     * userName : null
     * userPhoto : https://www.firstcard.sa/beta/uploads/users/no_avatar.png
     * url : https://www.firstcard.sa/beta/api.php?mod=news&nid=13
     * next : none
     * prev : https://www.firstcard.sa/beta/api.php?mod=news&nid=12
     */

    private String catId;
    private String catName;
    private String newsid;
    private String title;
    private String photo;
    private String lastUpdate;
    private ArDateBean ar_date;
    private String date;
    private String time;
    private String description;
    private String share;
    private int views;
    private int numberOfComments;
    private int numberOfLikes;
    private int numberOfDislikes;
    private String userId;
    private String userPhoto;
    private String url;
    private String next;
    private String prev;
    private String networkid;

    public String getNetworkid(){
        return networkid;
    }
    public void setNetworkid(String networkid){
        this.networkid = networkid;
    }
    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public ArDateBean getAr_date() {
        return ar_date;
    }

    public void setAr_date(ArDateBean ar_date) {
        this.ar_date = ar_date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public void setNumberOfDislikes(int numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public static class ArDateBean {
        /**
         * nameday : الاثنين
         * day : 18
         * namemonth : سبتمبر
         * year : 2017
         */

        private String nameday;
        private String day;
        private String namemonth;
        private String year;

        public String getNameday() {
            return nameday;
        }

        public void setNameday(String nameday) {
            this.nameday = nameday;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getNamemonth() {
            return namemonth;
        }

        public void setNamemonth(String namemonth) {
            this.namemonth = namemonth;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }
}
