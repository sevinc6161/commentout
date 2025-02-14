package com.example.denemecomment;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.denemecomment.controller.AdManager;
import com.example.denemecomment.controller.NewsController;
import com.example.denemecomment.util.Utils;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsController newsController;

    private ViewPager2 viewPager2;
    private AdView adView;
    private NestedScrollView nestedScroll;

    private ProgressBar progressBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View contentView = findViewById(R.id.main);
        viewPager2 = findViewById(R.id.featuredViewPage);
        Utils.applyWindowInsets(contentView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        nestedScroll = findViewById(R.id.nestedScroll);
        progressBar = findViewById(R.id.progressBar);
        newsController = new NewsController();

        showLoadingBar();

       // Utils.loadNewsList(this, newsController, recyclerView);

        Utils.denemeLoad(this, newsController, recyclerView,this);

        Utils.loadNewsFeatureList(this, newsController, recyclerView, viewPager2);

        hideLoadingBar();

        adView = findViewById(R.id.adView);

        AdManager adManager = new AdManager(this);
        adManager.ShowBannerAd(adView);


    }


    private void showLoadingBar() {
        progressBar.setVisibility(View.VISIBLE);
        nestedScroll.setVisibility(View.GONE);


    }


    private void hideLoadingBar() {
        progressBar.setVisibility(View.GONE);
        nestedScroll.setVisibility(View.VISIBLE);


    }


}

