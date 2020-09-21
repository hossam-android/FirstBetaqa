package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/19/17.
 */

public class PageDataModel {


    /**
     * pageid : 29
     * title : من نحن
     * content : <img src='https://www.firstcard.sa/beta/uploads/thumb/upload_2017_09_18_13_16_2483.jpg' style='width: 100%;' /><div style='direction: rtl;font-family:GESSTwoMedium-Medium;' ><br><h3>من نحن</h3><p><font style="font-size: 11px;">البطاقة الأولى كارد هو كارد يوفر لحاميله خصومات و عروض لأكثر من مائة علامة تجارية وهو نمط حياة بأكملها يقدم لك بطاقة خصم على جميع احتيجاتك (مستشفيات ، أكثر من 100 علامات تجارية ، مطاعم ، فنادق …)   التي تناسب احتياجاتك وميزانيتك حيث يصل الخصم الفوري إلى 25% وقسائم استردادية تصل إلى 35%.</font><br></p></div>
     * lastupdate : 2017-09-18 13:16:00
     * url : https://www.firstcard.sa/beta/api.php?mod=pages&pageid=29
     */

    private String pageid;
    private String title;
    private String content;
    private String lastupdate;
    private String url;

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
