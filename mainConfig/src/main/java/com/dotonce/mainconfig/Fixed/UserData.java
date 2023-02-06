package com.dotonce.mainconfig.Fixed;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {

    private final SharedPreferences.Editor editor;
    private final SharedPreferences sharedPreferences;

    public UserData(Context context) {
        editor = context.getSharedPreferences("appMainUser", Context.MODE_PRIVATE).edit();
        sharedPreferences = context.getSharedPreferences("appMainUser", Context.MODE_PRIVATE);
    }

    public void setUserSettings(String id, String name, String phone, String email,
                                String icon, String premium, String banned,
                                String banned_message, long banned_time, long last_active,
                                String country_id, String extra1, String extra2, String extra3,
                                String extra4, String extra5){

        editor.putString("id",id);
        editor.putString("name",name);
        editor.putString("phone",phone);
        editor.putString("email",email);
        editor.putString("icon",icon);
        editor.putString("banned_message",banned_message);
        editor.putString("country_id",country_id);
        editor.putString("extra1",extra1);
        editor.putString("extra2",extra2);
        editor.putString("extra3",extra3);
        editor.putString("extra4",extra4);
        editor.putString("extra5",extra5);
        editor.putString("premium",premium);
        editor.putString("banned",banned);
        editor.putLong("banned_time",banned_time);
        editor.putLong("last_active",last_active);
        editor.apply();

    }

    public String getId(){return sharedPreferences.getString("id","");}
    public String getName(){return sharedPreferences.getString("name","");}
    public String getPhone(){return sharedPreferences.getString("phone","");}
    public String getEmail(){return sharedPreferences.getString("email","");}
    public String getIcon(){return sharedPreferences.getString("icon","");}
    public String getBannedMessage(){return sharedPreferences.getString("banned_message","");}
    public String getCountryId(){return sharedPreferences.getString("country_id","");}
    public String getExtra1(){return sharedPreferences.getString("extra1","");}
    public String getExtra2(){return sharedPreferences.getString("extra2","");}
    public String getExtra3(){return sharedPreferences.getString("extra3","");}
    public String getExtra4(){return sharedPreferences.getString("extra4","");}
    public String getExtra5(){return sharedPreferences.getString("extra5","");}
    public Boolean isPreimum(){
        return sharedPreferences.getString("premium", "0").equals("1");
    }
    public Boolean isBanned(){
        return sharedPreferences.getString("banned", "0").equals("1");
    }
    public long getBannedTime(){return sharedPreferences.getLong("banned_time",System.currentTimeMillis());}
    public long getLastActive(){return sharedPreferences.getLong("last_active",System.currentTimeMillis());}
}
