package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Prefs {

    public static void setPremium(Context context, boolean isPre){
        SharedPreferences.Editor editor = context.getSharedPreferences("Pre",Context.MODE_PRIVATE).edit();
        editor.putBoolean("Pre", isPre);
        editor.apply();
    }

    public static boolean isPremium(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Pre", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("Pre",false);
    }

    public static void goActivity(Activity activity, Class<?> purActivity){
        Intent intent = new Intent(activity, purActivity);
        activity.startActivity(intent);
    }

}