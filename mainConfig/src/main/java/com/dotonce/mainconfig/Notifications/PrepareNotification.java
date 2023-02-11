package com.dotonce.mainconfig.Notifications;

import com.dotonce.mainconfig.MainFixed.MainServerConfig;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrepareNotification {
    static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static void executeTask(String... strings) {
        executorService.execute(() -> {
            try {
                String logo = MainServerConfig.COMMON_IP + "notifications_logo/" + strings[11];
                URL url = new URL("https://fcm.googleapis.com/fcm/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "key=" + strings[0]);
                conn.setRequestProperty("Content-Type", "application/json; UTF-8");
                JSONObject json = new JSONObject();
                json.put("to", "/topics/" + strings[4]);
                JSONObject info = new JSONObject();
                info.put("title", strings[1]);
                info.put("body", strings[2]);
                info.put("image", strings[3]);
                info.put("icon", logo );
                info.put("priority", "high");
                info.put("click_action", strings[5]);
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("extra1",strings[6]);
                jsonObject.put("extra2",strings[7]);
                jsonObject.put("extra3",strings[8]);
                jsonObject.put("extra4",strings[9]);
                jsonObject.put("activity",strings[10]);
                json.put("notification", info);
                json.put("data", jsonObject);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();
                conn.getInputStream();

            } catch (Exception | Error ignored) {

            }

        });
    }
}
