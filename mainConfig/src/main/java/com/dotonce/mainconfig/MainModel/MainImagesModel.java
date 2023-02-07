package com.dotonce.mainconfig.MainModel;

public class MainImagesModel {
    String id, version, url, isLink, action, action_ios, country_id,extra1,extra2;

    public MainImagesModel(String id, String version, String url, String isLink, String action, String action_ios, String country_id, String extra1, String extra2) {
        this.id = id;
        this.version = version;
        this.url = url;
        this.isLink = isLink;
        this.action = action;
        this.action_ios = action_ios;
        this.country_id = country_id;
        this.extra1 = extra1;
        this.extra2 = extra2;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public String getUrl() {
        return url;
    }

    public String getIsLink() {
        return isLink;
    }

    public String getAction() {
        return action;
    }

    public String getAction_ios() {
        return action_ios;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getExtra1() {
        return extra1;
    }

    public String getExtra2() {
        return extra2;
    }
}
