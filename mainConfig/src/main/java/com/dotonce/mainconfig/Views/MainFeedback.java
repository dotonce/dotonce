package com.dotonce.mainconfig.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.paris.Paris;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dotonce.mainconfig.Interfaces.onDialogAction;
import com.dotonce.mainconfig.MainFixed.AppCompatClass;
import com.dotonce.mainconfig.MainFixed.AppMainSettings;
import com.dotonce.mainconfig.MainFixed.CheckLanguage;
import com.dotonce.mainconfig.MainFixed.LoadingLayout;
import com.dotonce.mainconfig.MainFixed.MainDialog;
import com.dotonce.mainconfig.MainFixed.MainServerConfig;
import com.dotonce.mainconfig.MainFixed.MainTopics;
import com.dotonce.mainconfig.MainFixed.MySSL;
import com.dotonce.mainconfig.Notifications.Notification;
import com.dotonce.mainconfig.R;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainFeedback extends AppCompatClass {
    private String toolbar_title, toolbar_subtitle, email, phone,country_id,package_name,key;
    private TextView text_email;
    private EditText edit_description;
    private MaterialButton btn_send;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feedback);
        getBundle();
        setMethods(toolbar_title,toolbar_subtitle);
    }

    private void getBundle(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            toolbar_title = bundle.getString("toolbar_title","Feedback");
            toolbar_subtitle = bundle.getString("toolbar_subtitle","");
            email = bundle.getString("email","");
            phone = bundle.getString("phone","");
            country_id = bundle.getString("country_id","");
            package_name = bundle.getString("package_name","");
            key = bundle.getString("key","");
        }
    }

    @Override
    public void setInitialize() {
        super.setInitialize();
        btn_send = findViewById(R.id.btn_send);
        text_email = findViewById(R.id.email);
        edit_description = findViewById(R.id.description);
        relativeLayout = findViewById(R.id.relativeLayout);
    }

    @Override
    public void setActions() {
        super.setActions();
        try {
            Paris.style(text_email).apply(AppMainSettings.feedbackEmail);
            Paris.style(edit_description).apply(AppMainSettings.feedbackDescription);
            Paris.style(relativeLayout).apply(AppMainSettings.main_background);
            Paris.style(btn_send).apply(AppMainSettings.main_btn);

        }catch (Exception | Error ignored){}
          if(email.equals("") && phone.equals("")){
            text_email.setVisibility(View.GONE);
        }
        if(!email.equals("")){
            text_email.setText(email);
        }
        if(!phone.equals("")){
            text_email.setText(phone);
        }
        btn_send.setOnClickListener(v -> send());
    }

    private void send(){
        if(edit_description.getText().toString().trim().equals("")){
            Toast.makeText(this, getString(R.string.enter_a_message), Toast.LENGTH_SHORT).show();
        }else {

            MainDialog.show(MainFeedback.this, R.drawable.ic_send_feedback, getString(R.string.send),
                    getString(R.string.send_message),
                   getString(R.string.send), getString(R.string.cancel), true, new onDialogAction() {
                        @Override
                        public void onOkClick() {
                            LoadingLayout.show(MainFeedback.this);
                            StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, MainServerConfig.COMMON_IP +
                                    "insert_feedback.php?database=commonData&key="+key , response -> {
                                Toast.makeText(MainFeedback.this, response.trim(), Toast.LENGTH_SHORT).show();
                                LoadingLayout.hide(MainFeedback.this);
                                edit_description.setText("");
                                String from = phone;
                                if(from.equals("")){
                                    from = email;
                                }
                                Notification.Send(MainTopics.dotonce_api, "New "+toolbar_title+" from "+ from, edit_description.getText().toString(),
                                        "", MainTopics.dotonce_admin, MainTopics.dotonce_splash, package_name, "0", "0","","Feedbacks","");
                            }, error -> {
                            }
                            ) {

                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> map = new HashMap<>();
                                    map.put("phone", phone);
                                    map.put("email", email);
                                    map.put("description", edit_description.getText().toString());
                                    map.put("country_id", country_id);
                                    map.put("package_name", package_name);
                                    map.put("lang", CheckLanguage.getLanguage(MainFeedback.this));
                                    return map;
                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(MainFeedback.this, MySSL.getCert(MainFeedback.this));
                            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                                    (int) TimeUnit.SECONDS.toMillis(20),
                                    2,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            requestQueue.add(stringRequest);
                        }

                        @Override
                        public void onCancelClick() {
                        }
                    });



        }
        }
    }
