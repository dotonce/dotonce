package com.dotonce.mainconfig.MainFixed;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainAds {

    Context context;
    Locale locale;
    SimpleDateFormat format;
    SharedPreferences.Editor editor;
    SharedPreferences shared;
    public MainAds(Context context) {
        this.context = context;
        locale = new Locale("en");
        format = new SimpleDateFormat("yy/MM/dd HH:mm:ss",locale);
        editor = context.getSharedPreferences("AdType",Context.MODE_PRIVATE).edit();
        shared = context.getSharedPreferences("AdType",Context.MODE_PRIVATE);
    }

    public void setTimeAdmob(String time){
        editor.putString("admob", time);
        editor.apply();
    }
    public void setTimeUnity(String time){
        editor.putString("unity", time);
        editor.apply();
    }
    public void setTimeAdmobBanner(String time){
        editor.putString("admob_banner", time);
        editor.apply();
    }
    public void setTimeFacebook(String time){
        editor.putString("facebook", time);
        editor.apply();
    }
    public String getTimeAdmob(){
        return shared.getString("admob", String.valueOf(System.currentTimeMillis()));
    }
    public String getTimeUnity(){
        return shared.getString("unity", String.valueOf(System.currentTimeMillis()));
    }
    public String getTimeFacebook(){
        return shared.getString("facebook", String.valueOf(System.currentTimeMillis()));
    }
    public String getTimeAdmobBanner(){
        return shared.getString("admob_banner", String.valueOf(System.currentTimeMillis()));
    }


    public long getCalculatedInSeconds(String type){
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(ConvertTime.convertTime2(System.currentTimeMillis()));
            d2 = format.parse(ConvertTime.convertTime2(System.currentTimeMillis()));
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        long diff = 0;
        if (d2 != null) {
            if (d1 != null) {
                diff = d2.getTime() - d1.getTime();
            }
        }
        long diffSeconds = diff / 1000;
        return diffSeconds / (60 * 60 * 1000);
    }

    public long getCalculatedTimeFacebook(){
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(ConvertTime.convertTime2(Long.parseLong(getTimeFacebook())));
            d2 = format.parse(ConvertTime.convertTime2(System.currentTimeMillis()));
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        long diff = 0;
        if (d2 != null) {
            if (d1 != null) {
                diff = d2.getTime() - d1.getTime();
            }
        }

        return diff / (60 * 1000);
    }
    public long getCalculatedTimeAdmobBanner(){
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(ConvertTime.convertTime2(Long.parseLong(getTimeAdmobBanner())));
            d2 = format.parse(ConvertTime.convertTime2(System.currentTimeMillis()));
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        long diff = 0;
        if (d2 != null) {
            if (d1 != null) {
                diff = d2.getTime() - d1.getTime();
            }
        }

        return diff / (60 * 1000);
    }
    public long getCalculatedTimeUnity(){
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(ConvertTime.convertTime2(Long.parseLong(getTimeUnity())));
            d2 = format.parse(ConvertTime.convertTime2(System.currentTimeMillis()));
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        long diff = 0;
        if (d2 != null) {
            if (d1 != null) {
                diff = d2.getTime() - d1.getTime();
            }
        }

        return diff / (60 * 1000);
    }

    public long getCalculatedTimeAdmob(){
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(ConvertTime.convertTime2(Long.parseLong(getTimeAdmob())));
            d2 = format.parse(ConvertTime.convertTime2(System.currentTimeMillis()));
        } catch (Exception | Error e) {
            e.printStackTrace();
        }

        long diff = 0;
        if (d2 != null) {
            if (d1 != null) {
                diff = d2.getTime() - d1.getTime();
            }
        }

        return diff / (60 * 60 * 1000);
    }
}
