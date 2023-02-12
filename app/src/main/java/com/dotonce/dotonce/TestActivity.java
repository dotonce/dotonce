package com.dotonce.dotonce;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dotonce.mainconfig.MainAdapter.MainImagesAdapter;
import com.dotonce.mainconfig.MainFixed.AppMainSettings;
import com.dotonce.mainconfig.MainFixed.CheckUpdate;
import com.dotonce.mainconfig.Views.AppMainActivity;

public class TestActivity extends AppMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setMethods("", "");
        AppMainSettings.mainDialog_background = R.style.mainBackground;
        AppMainSettings.mainDialog_titleStyle = R.style.detail;
        AppMainSettings.mainDialog_messageStyle = R.style.detail;
        AppMainSettings.mainDialog_buttonsStyle = R.style.mainButton;

    }
    @Override
    public void onResume() {
        super.onResume();
        //CheckUpdate.check(TestActivity.this,0,"");
        removeRunnable();
        try {
            startAutoSlider(mainImagesAdapter.getCount());
        }catch (Error | Exception ignored){}
    }
}