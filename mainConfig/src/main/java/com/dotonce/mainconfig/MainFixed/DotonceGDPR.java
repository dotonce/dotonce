package com.dotonce.mainconfig.MainFixed;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.ads.MobileAds;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class DotonceGDPR {
    private final String TAG = "gdpr";
    private final Activity activity;

    public DotonceGDPR(Activity activity) {
        this.activity = activity;
        playInt();
    }

    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);

    public void set(){
        ConsentRequestParameters parameters = new ConsentRequestParameters.Builder()
                .setTagForUnderAgeOfConsent(false).build();
        ConsentInformation consentInformation = UserMessagingPlatform.getConsentInformation(activity);
        consentInformation.requestConsentInfoUpdate(activity, parameters, () -> UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity, new ConsentForm.OnConsentFormDismissedListener() {
            @Override
            public void onConsentFormDismissed(@Nullable FormError formError) {
                if(formError != null){
                    Log.d(TAG, String.format("%s: %s",
                            formError.getErrorCode(),
                            formError.getMessage()));
                }
                if (consentInformation.canRequestAds()) {
                    initializeMobileAdsSdk();
                }
            }
        }), formError -> Log.d(TAG, String.format("%s: %s",
                formError.getErrorCode(),
                formError.getMessage())));
        if (consentInformation.canRequestAds()) {
            initializeMobileAdsSdk();
        }
    }

    private void initializeMobileAdsSdk(){
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return;
        }
        MobileAds.initialize(activity);
    }

    void  playInt(){
        try {
            FirebaseApp.initializeApp(activity);
            FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
            firebaseAppCheck.installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance());
        }catch (Exception | Error ignored){}

    }
}
