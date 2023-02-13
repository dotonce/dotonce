package com.dotonce.mainconfig.Views;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.airbnb.paris.Paris;
import com.dotonce.mainconfig.MainFixed.AppCompatClass;
import com.dotonce.mainconfig.MainFixed.AppMainSettings;
import com.dotonce.mainconfig.MainFixed.LoadingLayout;
import com.dotonce.mainconfig.R;

public class MainPolicy extends AppCompatClass {
    private  WebView webView;
    private RelativeLayout relativeLayout;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        setMethods(getString(R.string.policy),"");
    }

    @Override
    public void setInitialize() {
        super.setInitialize();
        webView=findViewById(R.id.webView);
        relativeLayout=findViewById(R.id.relativeLayout);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            url = bundle.getString("url","");
        }
        LoadingLayout.show(MainPolicy.this);
    }

    @Override
    public void setActions() {
        super.setActions();
        Paris.style(relativeLayout).apply(AppMainSettings.main_background);
        webView.setWebViewClient(new webViewClient());
        webView.loadUrl(url);
    }

    public class webViewClient extends android.webkit.WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            LoadingLayout.hide(MainPolicy.this);
        }
    }
}