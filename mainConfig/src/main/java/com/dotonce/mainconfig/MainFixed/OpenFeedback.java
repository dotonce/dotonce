package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.content.Intent;

import com.dotonce.mainconfig.Views.MainFeedback;

public class OpenFeedback {
    public static void open(Activity activity, String toolbar_title,
                            String toolbar_subtitle,
                            String email, String phone,String country_id, String packageName,String key){
        Intent intent = new Intent(activity, MainFeedback.class);
        intent.putExtra("toolbar_title",toolbar_title);
        intent.putExtra("toolbar_subtitle",toolbar_subtitle);
        intent.putExtra("email",email);
        intent.putExtra("phone",phone);
        intent.putExtra("package_name",packageName);
        intent.putExtra("country_id",country_id);
        intent.putExtra("key",key);
        activity.startActivity(intent);
    }

}
