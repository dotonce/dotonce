package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.dotonce.mainconfig.Interfaces.onDialogAction;
import com.dotonce.mainconfig.R;

public class CheckUpdate {
    public static void check(Activity activity, int appVersion, String packageName){
        ConfigurationClass configurationClass= new ConfigurationClass(activity);
        if(Integer.parseInt(configurationClass.getVersion()) > appVersion){
            if(configurationClass.isRequireUpdate()){
                MainDialog.show(activity, R.drawable.ic_update, activity.getString(R.string.update),
                        activity.getString(R.string.update_message) + "\n\n\n" + configurationClass.getUpdateLog(),
                        activity.getString(R.string.update), activity.getString(R.string.exit), true, new onDialogAction() {
                            @Override
                            public void onOkClick() {
                                Uri uri = Uri.parse("market://details?id=" + packageName);
                                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                }
                                try {
                                    activity.startActivity(goToMarket);
                                } catch (ActivityNotFoundException e) {
                                    activity.startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
                                }
                                activity.finish();

                            }

                            @Override
                            public void onCancelClick() {
                                activity.finish();

                            }
                        });
            }else{
                MainDialog.show(activity, R.drawable.ic_update, activity.getString(R.string.update),
                        activity.getString(R.string.update_message) + "\n\n\n" + configurationClass.getUpdateLog(),
                        activity.getString(R.string.update), activity.getString(R.string.later), true, new onDialogAction() {
                            @Override
                            public void onOkClick() {
                                Uri uri = Uri.parse("market://details?id=" + packageName);
                                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                }
                                try {
                                    activity.startActivity(goToMarket);
                                } catch (ActivityNotFoundException e) {
                                    activity.startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
                                }
                            }

                            @Override
                            public void onCancelClick() {

                            }
                        });
            }

        }


    }
}
