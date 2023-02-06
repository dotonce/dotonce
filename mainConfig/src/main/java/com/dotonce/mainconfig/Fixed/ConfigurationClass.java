package com.dotonce.mainconfig.Fixed;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

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

    public void setDataFromServer(String packageName , OnMainImagesLoaded onMainImagesLoaded){
         StringRequest stringRequest = new StringRequest(Request.Method.GET, MainServerConfig.COMMON_IP+"select_configuration.php?key=0372&package="+packageName, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = new JSONArray(jsonObject.getJSONObject("data").getString("configuration"));
                if(jsonArray.length() > 0){
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
                setSettings(id,packageName2, version, version_ios,update_log,
                        api, admob_id,admob_banner_id, admob_time,unity_game_id,
                        unity_placement_id,unity_time,facebook_id,facebook_time,ad_type,
                        splash_ad_type, app_version);
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
                            String name = jSONObject1.getString("name");
                            String packageName22 = jSONObject1.getString("package");
                            String extra1 = jSONObject1.getString("extra1");
                            String extra2 = jSONObject1.getString("extra2");
                            String country_id = jSONObject1.getString("country_id");

                            arrayList.add(new MainImagesModel(id2, packageName22, name, isLink, action, action_ios,country_id,extra1,extra2));
                            i++;
                        }
                        onMainImagesLoaded.onLoaded(arrayList);

                        }
                }
            } catch (Exception | Error ignored) {

            }
        }, error -> {
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


     private void setSettings(String id,	String packageName,	String version,	String version_ios,	String update_log,
                             String api, String admob_id,	String admob_banner_id,	String admob_time,
                                     String unity_game_id ,String unity_placement_id,	String unity_time,	String facebook_id,
                                     String facebook_time, String ad_type,	String splash_ad_type,	String app_version){

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


}
