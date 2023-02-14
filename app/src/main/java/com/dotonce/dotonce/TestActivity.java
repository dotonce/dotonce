package com.dotonce.dotonce;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dotonce.mainconfig.Interfaces.OnResponseOne;
import com.dotonce.mainconfig.Interfaces.OnResponsetwo;
import com.dotonce.mainconfig.MainAdapter.MainImagesAdapter;
import com.dotonce.mainconfig.MainFixed.AppMainSettings;
import com.dotonce.mainconfig.MainFixed.CheckUpdate;
import com.dotonce.mainconfig.MainFixed.LoadingLayout;
import com.dotonce.mainconfig.MainFixed.MainTopics;
import com.dotonce.mainconfig.MainFixed.OpenFeedback;
import com.dotonce.mainconfig.MainFixed.OpenPrivacyPolicy;
import com.dotonce.mainconfig.Views.AppMainActivity;

public class TestActivity extends AppMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setMethods("", "");
        TextView textView = findViewById(R.id.text1);
        setInit();
        LoadingLayout.show(TestActivity.this);
        AppMainSettings.main_background = R.style.mainBackground;
        AppMainSettings.main_detail = R.style.title;
        AppMainSettings.main_title = R.style.detail;
        AppMainSettings.main_btn = R.style.mainButton;
        MainTopics.dotonce_api ="BAoTU7XNNyCVFUFor0Sl3KcmCqQP3EMCr8EwWSTqnD8GT3l0Kv5H6Nxaf_ttUOt-xWt_jshiKiyXA4gnN5BWhvQ";
        OpenFeedback.open(TestActivity.this,"","","","+96170208913","","","0372");

       configurationClass.setDataFromServer(BuildConfig.VERSION_CODE, "https://dotonce.com/info/", "", "infoeveryday", getPackageName(), "", "", "",
               "media", arrayList -> {
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
               }, new OnResponseOne() {
                   @Override
                   public void onResponseOne(String response) {


                   }

                   @Override
                   public void onFailedOne(String error) {

                   }
               }, new OnResponsetwo() {
                   @Override
                   public void onResponseTwo(String response) {

                   }

                   @Override
                   public void onFailedTwo(String error) {

                   }
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