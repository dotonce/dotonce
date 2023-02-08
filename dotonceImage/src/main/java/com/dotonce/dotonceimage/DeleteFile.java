package com.dotonce.dotonceimage;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 *we use delete_file.php
 */
public class DeleteFile {
    public static void delete(Activity activity, String ip, String file, String folder) {
        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, ip + "delete_file.php", response ->
        {

        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("file", file);
                map.put("folder", folder);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
       

        requestQueue.add(stringRequest);
    }
}
