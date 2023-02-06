package com.dotonce.mainconfig.Easy;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dotonce.mainconfig.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class LoadingLayout {

    public static void show(Activity activity, int background, int progressColor, float alpha){
        FrameLayout layout_loading = activity.findViewById(R.id.layout_loading);
        CircularProgressIndicator progressIndicator = activity.findViewById(R.id.progress_circular);
        TextView textView = activity.findViewById(R.id.background);
        textView.setBackgroundColor(activity.getResources().getColor(background));
        textView.setAlpha(alpha);
        progressIndicator.setIndicatorColor(activity.getResources().getColor(progressColor));
        layout_loading.setVisibility(View.VISIBLE);
    }

    public static void show(Activity activity){
        FrameLayout layout_loading = activity.findViewById(R.id.layout_loading);
        layout_loading.setVisibility(View.VISIBLE);
    }

    public static void hide(Activity activity){
        FrameLayout layout_loading = activity.findViewById(R.id.layout_loading);
        layout_loading.setVisibility(View.GONE);
    }
}
