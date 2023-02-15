package com.dotonce.mainconfig.MainFixed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;

import com.dotonce.mainconfig.R;
import com.google.android.material.appbar.MaterialToolbar;


public class SettingClass {
    private final Activity activity;
    private final String title;
    private final String subtitle;

    public SettingClass(Activity activity,String title, String subtitle) {
        this.activity = activity;
        this.title = title;
        this.subtitle = subtitle;

    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void setSetting(){
        MaterialToolbar materialToolbar = activity.findViewById(R.id.generalToolbar);
        materialToolbar.setNavigationIcon(activity.getResources().getDrawable(R.drawable.ic_back));
        materialToolbar.setNavigationIconTint(activity.getResources().getColor(AppMainSettings.toolbar_navigation_tint));
        materialToolbar.setNavigationOnClickListener(v -> activity.onBackPressed());
        materialToolbar.setTitle(title);
        materialToolbar.setSubtitle(subtitle);
        materialToolbar.setTitleTextAppearance(activity, AppMainSettings.toolbar_titleStyle);
        materialToolbar.setSubtitleTextAppearance(activity, AppMainSettings.toolbar_subtitleStyle);
        materialToolbar.setBackgroundColor(activity.getResources().getColor(R.color.transparent));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(AppMainSettings.toolbar_statusColor));
        }

    }
}
