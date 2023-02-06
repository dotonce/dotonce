package com.dotonce.mainconfig.Fixed;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManger {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    public LanguageManger(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("lan",Context.MODE_PRIVATE);
    }
    public void updateResource(String code){

        Locale locale=new Locale(code);
        Locale.setDefault(locale);
        Resources resources=context.getResources();
        Configuration config=resources.getConfiguration();
        config.locale=locale;
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
        setLanguage(code);


     
    }
    public void setLanguage(String code){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("appLan",code);
        editor.apply();
    }
    public String getLanguage(){
        return sharedPreferences.getString("appLan","en");
    }
}
