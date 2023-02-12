package com.dotonce.dotonce;

import android.os.Bundle;
import android.view.View;

import com.dotonce.mainconfig.MainAdapter.MainImagesAdapter;
import com.dotonce.mainconfig.MainFixed.AppMainSettings;
import com.dotonce.mainconfig.MainFixed.CheckUpdate;
import com.dotonce.mainconfig.Views.AppMainActivity;

public class TestActivity extends AppMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.dotonce.mainconfig.R.layout.activity_app_main);
        setMethods("", "");
        AppMainSettings.mainDialog_background = R.style.mainBackground;
        AppMainSettings.mainDialog_titleStyle = R.style.detail;
        AppMainSettings.mainDialog_messageStyle = R.style.detail;
        AppMainSettings.mainDialog_buttonsStyle = R.style.mainButton;

        configurationClass.setDataFromServer(3,"","","", "", "", "", arrayList -> {
            mainImagesAdapter = new MainImagesAdapter(TestActivity.this, arrayList);
            viewPager.setAdapter(mainImagesAdapter);
            dotsIndicator.attachTo(viewPager);
            removeRunnable();
            if(arrayList.size() == 0){
                layout_images.setVisibility(View.GONE);
            }
            else {
                layout_images.setVisibility(View.VISIBLE);
            }
            try {
                startAutoSlider(mainImagesAdapter.getCount());
            }catch (Error | Exception ignored){}
        });

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