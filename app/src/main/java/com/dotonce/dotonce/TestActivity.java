package com.dotonce.dotonce;

import android.os.Bundle;
import android.view.View;

import com.dotonce.mainconfig.Interfaces.OnResponseOne;
import com.dotonce.mainconfig.Interfaces.OnResponsetwo;
import com.dotonce.mainconfig.MainAdapter.MainImagesAdapter;
import com.dotonce.mainconfig.MainFixed.AppMainSettings;
import com.dotonce.mainconfig.MainFixed.LanguageManger;
import com.dotonce.mainconfig.Views.AppMainActivity;
import com.dotonce.mainconfig.Views.InformationCollect;
import com.dotonce.video.OpenVideoActivity;

public class TestActivity extends AppMainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setMethods("", "");

        setInit();
        //LoadingLayout.show(TestActivity.this);
        AppMainSettings.main_background = R.style.mainBackground;
        AppMainSettings.main_detail = R.style.title;
        AppMainSettings.main_title = R.style.detail;
        AppMainSettings.main_btn = R.style.mainButton;
        LanguageManger languageManger = new LanguageManger(TestActivity.this);
        languageManger.setLanguage("ar");
        InformationCollect.show(TestActivity.this,true,true);
//        OpenVideoActivity.open(TestActivity.this,"https://dotonce.com/dollar/news_images/icon_1675965478118..mp4","الإحتباس الحراري");
//        //OpenFeedback.open(TestActivity.this,"","","","+96170208913","","","0372");
       configurationClass.setDataFromServer(BuildConfig.VERSION_CODE, "https://dotonce.com/info/", "", "infoeveryday", getPackageName(), "", "", "","","",
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

        //CheckUpdate.check(TestActivity.this,0,"");
        removeRunnable();
        try {
            startAutoSlider(mainImagesAdapter.getCount());
        }catch (Error | Exception ignored){}
    }
}