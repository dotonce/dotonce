package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.airbnb.paris.Paris;
import com.dotonce.mainconfig.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class LoadingLayout {

    public static void show(Activity activity){
        FrameLayout layout_loading = activity.findViewById(R.id.layout_loading);
        CircularProgressIndicator progress_circular = activity.findViewById(R.id.progress_circular_loading);
        progress_circular.setIndicatorColor(activity.getResources().getColor(AppMainSettings.mainProgressColor));
        layout_loading.setVisibility(View.VISIBLE);
    }

    public static void hide(Activity activity){
        FrameLayout layout_loading = activity.findViewById(R.id.layout_loading);
        layout_loading.setVisibility(View.GONE);
    }
}
