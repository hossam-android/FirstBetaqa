package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/20/17.
 */

public class OfferDetailsDataModel {


    /**
     * catName : العروض
     * catId : 101
     * newsid : 10
     * title : موبيلات ايفون
     * photo : https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_15_1072.png
     * lastUpdate :  منذ 2 يوم
     * ar_date : {"nameday":"الاثنين","day":"18","namemonth":"سبتمبر","year":"2017"}
     * date : 18-09-2017
     * time : 10:15 AM
     * description : خصومات تصل الى 25%
     * share : موبيلات ايفون
     خصومات تصل الى 25%
     تم النشر عن طريق تطبيق تطبيق البطاقة الاولى
     * views : 5
     * numberOfComments : 0
     * numberOfLikes : 0
     * numberOfDislikes : 0
     * content : <div style="direction:rtl;text-align:center;font-size:15px !important;font-weight:bold;"><p>خصومات تصل الى 25%&nbsp;</p><br><img src="https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5461.jpg" alt="" style="width:300px;height:225px;"><br><img src="https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5790.jpeg" alt="" style="width:300px;height:225px;"><br><img src="https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5917.png" alt="" style="width:300px;height:225px;"></div>
     * content_site : <p>خصومات تصل الى 25%&nbsp;</p><br><img src="https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5461.jpg" alt="" style="width:300px;height:225px;"><br><img src="https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5790.jpeg" alt="" style="width:300px;height:225px;"><br><img src="https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5917.png" alt="" style="width:300px;height:225px;">
     * photosList : ["https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5461.jpg","https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5790.jpeg","https://www.firstcard.sa/beta/uploads/photo/upload_2017_09_18_10_14_5917.png"]
     * userId : 0
     * userName : null
     * userPhoto : https://www.firstcard.sa/beta/uploads/users/no_avatar.png
     * url : https://www.firstcard.sa/beta/api.php?mod=news&nid=10
     * next : none
     * prev : https://www.firstcard.sa/beta/api.php?mod=news&nid=9
     */

    private String catName;
    private String catId;
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
    private String content;
    private String content_site;
    private String userId;
    private String userPhoto;
    private String url;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_site() {
        return content_site;
    }

    public void setContent_site(String content_site) {
        this.content_site = content_site;
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
