package com.dotonce.mainconfig.Views;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.dotonce.mainconfig.Interfaces.PublisherName;
import com.dotonce.mainconfig.Interfaces.onDialogAction;
import com.dotonce.mainconfig.MainAdapter.MainImagesAdapter;
import com.dotonce.mainconfig.MainFixed.AppCompatClass;
import com.dotonce.mainconfig.MainFixed.MainDialog;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInit();
    }

    private void setInit() {
        handler=new Handler();
        viewPager = findViewById(R.id.viewPager);
        dotsIndicator = findViewById(R.id.dots_indicator);
        layout_images = findViewById(R.id.layout_images);
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

    public void onExit(){
        MainDialog.show(AppMainActivity.this, R.drawable.ic_exit, getString(R.string.exit), getString(R.string.sure),
                getString(R.string.exit), getString(R.string.cancel), true, new onDialogAction() {
                    @Override
                    public void onOkClick() {
                        finish();
                    }

                    @Override
                    public void onCancelClick() {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        onExit();
    }

    public void shareApp(String packageName, String app_name) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = app_name +" \n  https://play.google.com/store/apps/details?id="+packageName+"\n Download now for free";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, app_name);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void rateApp(String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    public void moreApps(PublisherName publisher) {
        Uri url = Uri.parse("http://play.google.com/store/search?q=pub:"+publisher.toString());
        Intent launch = new Intent(Intent.ACTION_VIEW, url);
        startActivity(launch);
    }
}