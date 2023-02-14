package com.dotonce.mainconfig.MainFixed;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.io.File;

public class AppCompatClass extends AppCompatActivity implements MyClass {
    private SharedPreferences.Editor sharedEdit;
    public ConfigurationClass configurationClass;
    public UserData userData;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LanguageManger languageManger=new LanguageManger(this);
        languageManger.updateResource(languageManger.getLanguage());
        sharedEdit=getSharedPreferences("mode",MODE_PRIVATE).edit();
        checkTheme();
        configurationClass = new ConfigurationClass(this);
        userData = new UserData(this);
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public void checkTheme(){
        File file1 = new File("data/data/"+getPackageName()+"/shared_prefs/mode.xml");
        if (file1.exists()) {
            SharedPreferences prefGet = getSharedPreferences("mode", MODE_PRIVATE);
            int currentTheme = prefGet.getInt("mode", 0);
            switch (currentTheme){
                case 0:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case 1:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case 2:
                    detectDeviceTheme();
                    break;
            }
        }
        else {
            detectDeviceTheme();
        }
    }
    public void detectDeviceTheme(){
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                sharedEdit.putInt("mode",2);
                sharedEdit.putInt("theme",0);
                sharedEdit.apply();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sharedEdit.putInt("mode",2);
                sharedEdit.putInt("theme",1);
                sharedEdit.apply();
                break;
        }
    }
    public void setSetting(String title, String subtitle){
        SettingClass settingClass = new SettingClass(AppCompatClass.this, title, subtitle);
        settingClass.setSetting();
    }

    @Override
    public void setInitialize() {

    }

    @Override
    public void setActions() {

    }

    public void setMethods(String title, String subTitle){
        setInitialize();
        setSetting(title, subTitle);
        setActions();
    }
}
