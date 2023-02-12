package com.dotonce.mainconfig.Views;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.dotonce.mainconfig.MainAdapter.MainImagesAdapter;
import com.dotonce.mainconfig.MainFixed.AppCompatClass;
import com.dotonce.mainconfig.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class AppMainActivity extends AppCompatClass {
    private Runnable runnable=null;
    private Handler handler;
    public int slider_time= 3000;
    public ViewPager viewPager;
    public DotsIndicator dotsIndicator;
    public RelativeLayout layout_images;
    public MainImagesAdapter mainImagesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        setMethods("","");
    }

    @Override
    public void setInitialize() {
        super.setInitialize();
        handler=new Handler();
        viewPager = findViewById(R.id.viewPager);
        dotsIndicator = findViewById(R.id.dots_indicator);
        layout_images = findViewById(R.id.layout_images);
    }

    @Override
    public void setActions() {
        super.setActions();

    }
    @Override
    public void onPause() {
        removeRunnable();
        super.onPause();
    }

    @Override
    public void onStop() {
        removeRunnable();
        super.onStop();
    }

    public void removeRunnable(){
        try {
            handler.removeCallbacks(runnable);
        }catch (Error | Exception ignored){}
    }
    public void startAutoSlider(final int count) {
        try {
            runnable = () -> {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, slider_time);
            };
            handler.postDelayed(runnable, slider_time);

        }catch (Error | Exception ignored){}
    }



}