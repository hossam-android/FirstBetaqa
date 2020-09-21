package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/20/17.
 */

public class RequestCardDataModel {


    /**
     * pageid : 25
     * title : طلب بطاقة- التطبيق
     * content : <img src='https://www.firstcard.sa/beta/uploads/thumb/upload_2017_09_18_11_16_0151.jpg' style='width: 100%;' /><div style='direction: rtl;font-family:GESSTwoMedium-Medium;' ><p></p><h3>الرسوم<br></h3><p>رسوم البطاقة <span style="font-weight: bold;"><span style="color: rgb(255, 0, 0);">200 ريال</span></span> فقط والتوصيل مجاناً<br><br></p><h3>خطوات الانضمام.<br></h3><p>زيارة فروع الشركة او الاتصال بالرقم الموحد وطلب البطاقة او طلبها عن طريق الموقع او التطبيقات الخاصة بالجوال او الاتصال بأحد المناديب ويقوم بتوصيلها لكم مجاناً.<br><br><br></p><h3>المستندات المطلوبه للإشتراك<br></h3><ul><li>صورة الهوية الوطنية للمواطنين والخليجيين.</li></ul><ul><li>صوره الإقامة للمقيمين.</li></ul><ul><li>صورة الجواز والتأشيرة للزائرين والحجاج والمعتمرين.</li></ul><ul><li>صورة الهوية الوطنية أو بطاقة العائلة للنساء السعوديات.<br></li></ul></div>
     * lastupdate : 2017-09-18 11:27:00
     * url : https://www.firstcard.sa/beta/api.php?mod=pages&pageid=25
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
