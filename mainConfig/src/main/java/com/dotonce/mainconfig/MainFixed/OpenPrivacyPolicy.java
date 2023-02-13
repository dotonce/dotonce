package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.content.Intent;

import com.dotonce.mainconfig.Views.MainPolicy;

public class OpenPrivacyPolicy {
    public static void open(Activity activity, String url){
        Intent intent = new Intent(activity, MainPolicy.class);
        intent.putExtra("url",url);
        activity.startActivity(intent);
    }
}
