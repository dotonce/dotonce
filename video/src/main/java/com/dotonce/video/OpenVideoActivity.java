package com.dotonce.video;

import android.app.Activity;
import android.content.Intent;

public class OpenVideoActivity {
    public static void open(Activity activity, String url, String video_title, boolean fitXY, boolean isFromRaw, int rawFile){
        Intent intent = new Intent(activity, VideoActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",video_title);
        intent.putExtra("fitXY",fitXY);
        intent.putExtra("isFile",isFromRaw);
        intent.putExtra("rawFile",rawFile);
        activity.startActivity(intent);
    }
}
