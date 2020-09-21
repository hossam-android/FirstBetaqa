package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/18/17.
 */

public class NewsDetailsDataModel {


    /**
     * catName : الأخبار
     * catId : 92
     * newsid : 35
     * title :
     * photo : https://www.firstcard.sa/uploads/photo/upload_2017_10_09_09_52_4191.jpg
     * lastUpdate :  منذ 2 يوم
     * ar_date : {"nameday":"الاثنين","day":"09","namemonth":"اكتوبر","year":"2017"}
     * date : 09-10-2017
     * time : 09:52 AM
     * description : إنضم إلى شبكتنا الطبية مجمع عيادات الإبداع الطبى
     * share :
     إنضم إلى شبكتنا الطبية مجمع عيادات الإبداع الطبى
     تم النشر عن طريق تطبيق البطاقة الاولى The First Card
     * views : 27
     * numberOfComments : 0
     * numberOfLikes : 0
     * numberOfDislikes : 0
     * content : <div style="direction:rtl;text-align:center;font-size:15px !important;font-weight:bold;"><p>إنضم إلى شبكتنا الطبية مجمع عيادات الإبداع الطبى&nbsp;</p></div>
     * content2 : <div style="direction:rtl;text-align:center;font-size:15px !important;font-weight:bold;"><p>إنضم إلى شبكتنا الطبية مجمع عيادات الإبداع الطبى&nbsp;</p></div>
     * content_site : <p>إنضم إلى شبكتنا الطبية مجمع عيادات الإبداع الطبى&nbsp;</p>
     * photosList : none
     * userId : 0
     * userName : null
     * userPhoto : https://www.firstcard.sa/uploads/users/no_avatar.png
     * url : https://www.firstcard.sa/api.php?mod=news&nid=35
     * next : none
     * prev : https://www.firstcard.sa/api.php?mod=news&nid=34
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
    private String content2;
    private String content_site;
    private String photosList;
    private String userId;
    private Object userName;
    private String userPhoto;
    private String url;
    private String next;
    private String prev;

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

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent_site() {
        return content_site;
    }

    public void setContent_site(String content_site) {
        this.content_site = content_site;
    }

    public String getPhotosList() {
        return photosList;
    }

    public void setPhotosList(String photosList) {
        this.photosList = photosList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
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
         * day : 09
         * namemonth : اكتوبر
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
