package com.example.denemecomment.controller;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdManager {
    Activity activity;
    private InterstitialAd mInterstitialAd;


    public AdManager(Activity activity){
        this.activity = activity;
    }


    public void initAd(){
        if(AdConst.AD_TYPE.equalsIgnoreCase(AdConst.ADMOB_TYPE)){
            MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

                }
            });
        }
    }


    public void ShowBannerAd(AdView container){
        if(AdConst.AD_TYPE.equalsIgnoreCase(AdConst.ADMOB_TYPE)){
            AdView adView = new AdView(activity);
            adView.setAdSize(AdSize.LARGE_BANNER);
            adView.setAdUnitId(AdConst.ADMOB_BANNER_ID);
            container.addView(adView);

            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }
    }

    public void ShowMreBannerAd(AdView container){
        if(AdConst.AD_TYPE.equalsIgnoreCase(AdConst.ADMOB_TYPE)){
            AdView adView = new AdView(activity);
            adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
            adView.setAdUnitId(AdConst.ADMOB_BANNER_ID);
            container.addView(adView);

            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }
    }



    public void ShowInterstitialAd() {
        if (AdConst.AD_TYPE.equalsIgnoreCase(AdConst.ADMOB_TYPE)) {

            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity, AdConst.ADMOB_INTERSTITIAL_ID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd ad) {
                    mInterstitialAd = ad;

                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(activity);
                    }
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    mInterstitialAd = null;
                }
            });
        }
    }

}
