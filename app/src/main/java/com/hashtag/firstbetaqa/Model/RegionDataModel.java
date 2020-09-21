package com.hashtag.firstbetaqa.Model;

/**
 * Created by ebda3-mint on 9/18/17.
 */

public class RegionDataModel {


    /**
     * id : 3
     * name : الرياض
     * total : 0
     * catid : 1
     * region : 2
     * url : https://www.firstcard.sa/beta/api.php?mod=market&cid=1&city=3
     */

    private String id;
    private String name;
    private int total;
    private int catid;
    private int region;
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

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
