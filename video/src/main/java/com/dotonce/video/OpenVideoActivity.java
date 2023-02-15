package com.dotonce.video;

import android.app.Activity;
import android.content.Intent;

public class OpenVideoActivity {
    public static void open(Activity activity, String url, String video_title){
        Intent intent = new Intent(activity, VideoActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",video_title);
        activity.startActivity(intent);
    }
}
