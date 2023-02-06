package com.dotonce.dotonce;

import android.os.Bundle;

import com.dotonce.mainconfig.Views.AppMainActivity;

public class TestActivity extends AppMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.dotonce.mainconfig.R.layout.activity_app_main);
        setMethods("hussein", "Ali");


    }
}