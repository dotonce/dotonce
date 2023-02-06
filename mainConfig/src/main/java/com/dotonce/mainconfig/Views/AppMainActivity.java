package com.dotonce.mainconfig.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dotonce.mainconfig.Easy.AppMainSettings;
import com.dotonce.mainconfig.Fixed.AppCompatClass;
import com.dotonce.mainconfig.Fixed.MainAds;
import com.dotonce.mainconfig.R;

public class AppMainActivity extends AppCompatClass {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        setMethods("","");
    }

    @Override
    public void setInitialize() {
        super.setInitialize();
    }

    @Override
    public void setActions() {
        super.setActions();
    }
}