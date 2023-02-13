package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

public class CheckLanguage {
    public static String getLanguage(Context context){
        String lang = "en";
        File file1 = new File("data/data/"+context.getPackageName()+"/shared_prefs/lan.xml");
        if (file1.exists()) {
            SharedPreferences prefGet = context.getSharedPreferences("lan", Context.MODE_PRIVATE);
            lang= prefGet.getString("appLan", "en");
            }
        return lang;
    }
}
