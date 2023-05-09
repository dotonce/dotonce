package com.dotonce.mainconfig.MainFixed;

import android.content.Context;

import com.android.volley.toolbox.HurlStack;
import com.dotonce.mainconfig.R;

import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class MySSL {
    public static HurlStack getCert(Context context){
//        try {
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
//                    TrustManagerFactory.getDefaultAlgorithm());
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStore.load(context.getResources().openRawResource(R.raw.a), "password".toCharArray());
//            trustManagerFactory.init(keyStore);
//            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
//            return new HurlStack(null, sslContext.getSocketFactory());
//        }catch (Exception | Error ignored){}
        return null;
    }
}
