package com.dotonce.mainconfig.MainFixed;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dotonce.mainconfig.Interfaces.OnMainImagesLoaded;
import com.dotonce.mainconfig.MainModel.MainImagesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConfigurationClass {
    private final UserData userData;
    private final String TAG = "d_tag";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private ArrayList<MainImagesModel> arrayList;

    private final Context context;

     public ConfigurationClass(Context context){
         this.context = context;
         userData = new UserData(context);
         sharedPreferences = context.getSharedPreferences("appMainSettings", Context.MODE_PRIVATE);
         editor = context.getSharedPreferences("appMainSettings", Context.MODE_PRIVATE).edit();
     }

    public void setDataFromServer(int appVersion, String IP_AND_DIR, String countryId, String database, String packageName ,String phone, String email, OnMainImagesLoaded onMainImagesLoaded){
         StringRequest stringRequest = new StringRequest(Request.Method.GET, MainServerConfig.COMMON_IP+"select_configuration.php?key="+
                 MainServerConfig.DATABASE_VERSION+"&database="+database + "&package=" + packageName + "&phone="+phone+"&email="+email, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);

                try {
                    JSONArray jsonArray = new JSONArray(jsonObject.getJSONObject("data").getString("configuration"));
                    if(jsonArray.length() > 0) {
                        JSONObject jSONObject = jsonArray.getJSONObject(0);
                        String id = jSONObject.getString("id");
                        String packageName2 = jSONObject.getString("package");
                        String version = jSONObject.getString("version");
                        String version_ios = jSONObject.getString("version_ios");
                        String update_log = jSONObject.getString("update_log");
                        String api = jSONObject.getString("api");
                        String admob_id = jSONObject.getString("admob_id");
                        String admob_banner_id = jSONObject.getString("admob_banner_id");
                        String unity_game_id = jSONObject.getString("unity_game_id");
                        String unity_placement_id = jSONObject.getString("unity_placement_id");
                        String facebook_id = jSONObject.getString("facebook_id");
                        String app_version = jSONObject.getString("app_version");
                        String admob_time = jSONObject.getString("admob_time");
                        String unity_time = jSONObject.getString("unity_time");
                        String facebook_time = jSONObject.getString("facebook_time");
                        String ad_type = jSONObject.getString("ad_type");
                        String splash_ad_type = jSONObject.getString("splash_ad_type");
                        String require_update = jSONObject.getString("require_update");
                        setSettings(id, packageName2, version, version_ios, update_log,
                                api, admob_id, admob_banner_id, admob_time, unity_game_id,
                                unity_placement_id, unity_time, facebook_id, facebook_time, ad_type,
                                splash_ad_type, app_version,require_update);
                    }
                }catch (Exception | Error error){Log.d(TAG + "_CONFIG", error.toString());}


                try {
                    JSONArray jsonArray2 = new JSONArray(jsonObject.getJSONObject("data").getString("images"));
                    if(jsonArray2.length() > 0){
                        arrayList = new ArrayList<>();
                        int i =0;
                        while (i< jsonArray2.length()){
                            JSONObject jSONObject1 = jsonArray2.getJSONObject(i);
                            String id2 = jSONObject1.getString("id");
                            String isLink = jSONObject1.getString("isLink");
                            String action = jSONObject1.getString("action");
                            String action_ios = jSONObject1.getString("action_ios");
                            String name = IP_AND_DIR + "main_images/" + jSONObject1.getString("name");
                            String version = jSONObject1.getString("version");
                            String extra1 = jSONObject1.getString("extra1");
                            String extra2 = jSONObject1.getString("extra2");
                            String country_id = jSONObject1.getString("country_id");
                            int version2;
                            if(version.equals("")){
                                version2 = 9999;
                            }else {
                                version2 = Integer.parseInt(version);
                            }
                            if(version.equals("")  | (appVersion <= version2)){
                                if(country_id.equals("") | country_id.equals(countryId)){
                                arrayList.add(new MainImagesModel(id2, version, name, isLink, action, action_ios,country_id,extra1,extra2));
                                }
                            }
                            i++;
                        }
                        onMainImagesLoaded.onLoaded(arrayList);

                    }
                }catch (Exception | Error error){Log.d(TAG + "_IMAGES", error.toString());}


                try {
                    JSONArray jsonArray3 = new JSONArray(jsonObject.getJSONObject("data").getString("user"));
                    if(jsonArray3.length() > 0) {
                        JSONObject jSONObject3 = jsonArray3.getJSONObject(0);
                        String id3 = jSONObject3.getString("id");
                        String name = jSONObject3.getString("name");
                        String phone3 = jSONObject3.getString("phone");
                        String email3 = jSONObject3.getString("email");
                        String icon = jSONObject3.getString("icon");
                        String banned_message = jSONObject3.getString("banCause");
                        String country_id = jSONObject3.getString("country_id");
                        String extra1 = jSONObject3.getString("extra1");
                        String extra2 = jSONObject3.getString("extra2");
                        String extra3 = jSONObject3.getString("extra3");
                        String extra4 = jSONObject3.getString("extra4");
                        String extra5 = jSONObject3.getString("extra5");
                        String premium = jSONObject3.getString("premium");
                        String banned = jSONObject3.getString("banned");
                        String banned_time = jSONObject3.getString("banTime");
                        String last_active = jSONObject3.getString("last_active");
                        long banned_time2, last_active2;
                        if(banned_time.equals("")){
                            banned_time2 = System.currentTimeMillis();
                        }else {
                            banned_time2 = Long.parseLong(banned_time);
                        }
                        if(last_active.equals("")){
                            last_active2 = System.currentTimeMillis();
                        }else {
                            last_active2 = Long.parseLong(last_active);
                        }
                        userData.setUserSettings(id3, name,phone3, email3,icon,premium,banned,
                                banned_message,banned_time2,last_active2,country_id,extra1,extra2,
                                extra3,extra4,extra5);

                    }
                }catch (Exception | Error error){Log.d(TAG + "_USER", error.toString());}
            } catch (Exception | Error error) {Log.d(TAG + "_JSON", error.toString());
            }
        }, error -> Log.d(TAG + "_URL", error.toString()));

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


     private void setSettings(String id,	String packageName,	String version,	String version_ios,	String update_log,
                             String api, String admob_id,	String admob_banner_id,	String admob_time,
                                     String unity_game_id ,String unity_placement_id,	String unity_time,	String facebook_id,
                                     String facebook_time, String ad_type,	String splash_ad_type,	String app_version, String require_update){

                 editor.putString("id", id);
                 editor.putString("packageName", packageName);
                 editor.putString("version", version);
                 editor.putString("version_ios", version_ios);
                 editor.putString("update_log", update_log);
                 editor.putString("api", api);
                 editor.putString("admob_id", admob_id);
                 editor.putString("admob_banner_id", admob_banner_id);
                 editor.putString("unity_game_id", unity_game_id);
                 editor.putString("unity_placement_id", unity_placement_id);
                 editor.putString("facebook_id", facebook_id);
                 editor.putString("app_version", app_version);
                 editor.putString("require_update", require_update);
                 editor.putLong("admob_time", Long.parseLong(admob_time));
                 editor.putLong("unity_time", Long.parseLong(unity_time));
                 editor.putLong("facebook_time", Long.parseLong(facebook_time));
                 if(!userData.isPreimum()){
                     editor.putString("ad_type", ad_type);
                     editor.putString("splash_ad_type", splash_ad_type);
                 }
                 else {
                     editor.putString("ad_type", "4");
                     editor.putString("splash_ad_type", "4");
                 }
                 editor.apply();
     }

     public String getId(){return sharedPreferences.getString("id","");}
     public String getPackageName(){return sharedPreferences.getString("packageName","");}
     public String getVersion(){return sharedPreferences.getString("version","");}
     public String getVersionIOS(){return sharedPreferences.getString("version_ios","");}
     public String getAppVersion(){return sharedPreferences.getString("app_version","");}
     public String getUpdateLog(){return sharedPreferences.getString("update_log","");}
     public String getAdmobId(){return sharedPreferences.getString("admob_id","");}
     public String getUnityGameId(){return sharedPreferences.getString("unity_game_id","");}
     public String getAdmobBannerId(){return sharedPreferences.getString("admob_banner_id","");}
     public String getFacebookId(){return sharedPreferences.getString("facebook_id","");}
     public String getAdType(){return sharedPreferences.getString("ad_type","");}
     public String getSplashAdType(){return sharedPreferences.getString("splash_ad_type","");}
     public String getUnityPlacementId(){return sharedPreferences.getString("unity_placement_id","");}
     public String getApi(){return sharedPreferences.getString("api","");}
     public long getAdmobTime(){return sharedPreferences.getLong("admob_time",System.currentTimeMillis());}
     public long getUnityTime(){return sharedPreferences.getLong("unity_time",System.currentTimeMillis());}
     public long getFacebookTime(){return sharedPreferences.getLong("facebook_time",System.currentTimeMillis());}

    public Boolean isRequireUpdate(){
        return sharedPreferences.getString("require_update", "0").equals("1");
    }
}
