package com.dotonce.mainconfig.Notifications;

public class Notification {
    public static void Send(String api,String title,String message,String image,String topic,String click_action,String extra1,String extra2,String extra3,String extra4,String activity,String app_icon_name){
        PrepareNotification.executeTask(api,title,message,image,topic,click_action,extra1,extra2,extra3,extra4,activity,app_icon_name);
    }
}
