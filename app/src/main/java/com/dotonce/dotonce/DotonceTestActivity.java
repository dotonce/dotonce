package com.dotonce.dotonce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dotonce.mainconfig.MainFixed.ConfigurationClass;

public class DotonceTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dotonce_test);
        ConfigurationClass configurationClass = new ConfigurationClass(DotonceTestActivity.this);
        configurationClass.checkRequireDialog(0,R.mipmap.ic_launcher,"commonData","com.once.adstudio");
    }
}