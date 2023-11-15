package com.dotonce.image;


import android.net.Uri;

public class MediaModel {
    String name,type,id;
    Uri path;
    int viewType;

    public MediaModel(String name, Uri path, String type){
        this.name = name;
        this.path= path;
        this.type= type;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getName() {
        return name;
    }
    public Uri getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPath(Uri path) {
        this.path = path;
    }

    public void setType(String type) {
        this.type = type;
    }
}
