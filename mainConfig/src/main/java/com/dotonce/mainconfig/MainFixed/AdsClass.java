package com.dotonce.mainconfig.MainFixed;//package com.dotonce.dotonceadmin.CommonApps;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

import java.io.File;
import java.util.Calendar;

public class AdsClass implements IUnityAdsInitializationListener {
    Activity activity;
    boolean isUnityLoaded;
    MainAds mainAds;
    InterstitialAd interstitialAd;
    ConfigurationClass configurationClass;


    public AdsClass(Activity activity) {
        this.activity = activity;
        mainAds = new MainAds(activity);
        File file = new File("data/data/" +activity.getPackageName()+"/shared_prefs/ads2.xml");
        if(!file.exists()){
            SharedPreferences.Editor editor = activity.getSharedPreferences("ads2", Context.MODE_PRIVATE).edit();
            editor.putBoolean("set",true);
            editor.apply();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR_OF_DAY, -2);

            Calendar c2 = Calendar.getInstance();
            c2.add(Calendar.MINUTE, -3);
            mainAds.setTimeAdmob(String.valueOf(c.getTimeInMillis()));
            mainAds.setTimeUnity(String.valueOf(c2.getTimeInMillis()));
            mainAds.setTimeFacebook(String.valueOf(c2.getTimeInMillis()));
            mainAds.setTimeAdmobBanner(String.valueOf(c2.getTimeInMillis()));
            mainAds.setTimeApplovin(String.valueOf(c2.getTimeInMillis()));
        }

        configurationClass = new ConfigurationClass(activity);
        initializeUnityAds();
        //initializeAdmob();

    }

    @Deprecated
    public void showAd(){
        if(!Prefs.isPremium(activity)){
            if(configurationClass.getAdType().equals("1")){
                if(mainAds.getCalculatedTimeAdmob() >= configurationClass.getAdmobTime()) {
                    displayAdmob();
                }
                else {
                    if(mainAds.getCalculatedTimeUnity() >= configurationClass.getUnityTime()) {
                        displayUnity();
                    }
                }
            }else if(configurationClass.getAdType().equals("2")){
                if(mainAds.getCalculatedTimeUnity() >= configurationClass.getUnityTime()) {
                    displayUnity();
                }
            }
            else if(configurationClass.getAdType().equals("3")){
                if(mainAds.getCalculatedTimeFacebook() >= configurationClass.getFacebookTime()) {
                    // displayFacebook();
                }
            }

            else if(configurationClass.getAdType().equals("4")){
                if(mainAds.getCalculatedTimeApplovin() >= configurationClass.getApplovinTime()) {
                    // displayApplovin();
                }
            }
        }

    }
    public void showAdNoAdmob(){
        if(!Prefs.isPremium(activity)){
            if(!configurationClass.getAdType().equals("0")) {
                if (mainAds.getCalculatedTimeUnity() >= configurationClass.getUnityTime()) {
                    displayUnity();
                }
            }
        }
    }



    private void setAdmobSetting(){
        try {
            final FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    interstitialAd = null;

                }

                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                    interstitialAd = null;

                }

            };
            InterstitialAd.load(
                    activity,
                    configurationClass.getAdmobId(),
                    new AdRequest.Builder().build(),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd ad) {
                            interstitialAd = ad;
                            interstitialAd.setFullScreenContentCallback(fullScreenContentCallback);

                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                            interstitialAd = null;
                        }
                    });
        }catch (Exception | Error ignored){}
    }


    private void initializeUnityAds(){
        try {
            UnityAds.initialize(activity, configurationClass.getUnityGameId(), false, this);
        }catch (Exception | Error ignored){}
    }

    @Override
    public void onInitializationComplete() {
        loadUnityAd();
    }

    @Override
    public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

    }
    public void initializeAdmob(){
        if(configurationClass.getAdType().equals("1")) {
            if(mainAds.getCalculatedTimeAdmob() >= configurationClass.getAdmobTime()) {
                setAdmobSetting();
            }

        }
    }


    private void displayAdmob(){
        if(interstitialAd!=null){
            interstitialAd.show(activity);
            mainAds.setTimeAdmob(String.valueOf(System.currentTimeMillis()));
            mainAds.setTimeUnity(String.valueOf(System.currentTimeMillis()));
        }
    }

    private void displayUnity(){
        if(isUnityLoaded) {
            UnityAds.show(activity, configurationClass.getUnityPlacementId(), new UnityAdsShowOptions(), showListener);
            mainAds.setTimeUnity(String.valueOf(System.currentTimeMillis()));
        }
    }
    private void loadUnityAd () {
        try {
            UnityAds.load(configurationClass.getUnityPlacementId(), loadListener);
        }catch (Error | Exception ignored){}

    }

    private final IUnityAdsLoadListener loadListener = new IUnityAdsLoadListener() {
        @Override
        public void onUnityAdsAdLoaded(String placementId) {
            isUnityLoaded = true;
        }

        @Override
        public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
            isUnityLoaded = false;
        }
    };

    private final IUnityAdsShowListener showListener = new IUnityAdsShowListener() {
        @Override
        public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
        }

        @Override
        public void onUnityAdsShowStart(String placementId) {
        }

        @Override
        public void onUnityAdsShowClick(String placementId) {

        }

        @Override
        public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {

        }
    };
}
