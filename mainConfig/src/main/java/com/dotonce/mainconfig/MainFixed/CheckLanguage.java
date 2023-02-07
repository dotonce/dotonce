package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

public class CheckLanguage {
    private String lang;
    private final Activity activity;
    public CheckLanguage(Activity activity){
        this.activity=activity;
    }
    public String getLanguage(){
        File file1 = new File("data/data/"+activity.getPackageName()+"/shared_prefs/lan.xml");
        if (file1.exists()) {
            SharedPreferences prefGet = activity.getSharedPreferences("lan", Context.MODE_PRIVATE);
            lang= prefGet.getString("appLan", "en");
            }
        return lang;
    }
}
