package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/19/17.
 */

public class CommentDataModel {


    /**
     * username : احمد محمد
     * userid : 0
     * userphoto : https://www.firstcard.sa/beta/uploads/users/
     * commentid : 2000
     * content : dsafdsf adsf dsdsdsf تعليف ق جديد
     * stringtime :  منذ 13 ثانية
     * likesnumber : 0
     * dislikenumber : 0
     * date : 2017-09-19 09:06
     */

    private String username;
    private String userid;
    private String userphoto;
    private String commentid;
    private String content;
    private String stringtime;
    private int likesnumber;
    private int dislikenumber;
    private String date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStringtime() {
        return stringtime;
    }

    public void setStringtime(String stringtime) {
        this.stringtime = stringtime;
    }

    public int getLikesnumber() {
        return likesnumber;
    }

    public void setLikesnumber(int likesnumber) {
        this.likesnumber = likesnumber;
    }

    public int getDislikenumber() {
        return dislikenumber;
    }

    public void setDislikenumber(int dislikenumber) {
        this.dislikenumber = dislikenumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
