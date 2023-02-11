package com.dotonce.dotonce;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dotonce.mainconfig.Interfaces.OnMainImagesLoaded;
import com.dotonce.mainconfig.MainAdapter.MainImagesAdapter;
import com.dotonce.mainconfig.MainFixed.CheckUpdate;
import com.dotonce.mainconfig.MainFixed.ConvertTime;
import com.dotonce.mainconfig.MainModel.MainImagesModel;
import com.dotonce.mainconfig.Views.AppMainActivity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class TestActivity extends AppMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.dotonce.mainconfig.R.layout.activity_app_main);
        setMethods("", "    //**8");
//        AppMainSettings.mainDialog_background = R.color.white;
        Toast.makeText(this, ""+ConvertTime.getFullCalculatedTime(this,"318938413000"), Toast.LENGTH_SHORT).show();
        configurationClass.setDataFromServer(3,"https://dotonce.com/dollar/","","leb_dollar", "com.alayan.", "", "", arrayList -> {
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
        CheckUpdate.check(TestActivity.this,0,"");
        removeRunnable();
        try {
            startAutoSlider(mainImagesAdapter.getCount());
        }catch (Error | Exception ignored){}
    }
}