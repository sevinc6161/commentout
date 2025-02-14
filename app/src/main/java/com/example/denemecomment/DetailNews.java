package com.example.denemecomment;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denemecomment.controller.AdManager;
import com.example.denemecomment.controller.FilteredRelatedController;
import com.example.denemecomment.controller.FiltredController;
import com.example.denemecomment.controller.NewsController;
import com.example.denemecomment.controller.RelatedController;
import com.example.denemecomment.controller.RelatedTwoController;
import com.example.denemecomment.util.DetailNewsCallback;
import com.example.denemecomment.util.Utils;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;


public class DetailNews extends AppCompatActivity implements DetailNewsCallback {

    private FiltredController filtredController;
    private NewsController newsController;
    private RelatedController relatedController;
    private FilteredRelatedController filteredRelatedController;
    private TextView title, context ;
    private ImageView imageView,shareImage;
    private RecyclerView recyclerView;
    private AdView adViewMre;
    private AdManager adManager;

    private TextView textView;

    private WebView webView;
    private RelatedTwoController relatedTwoController;
    ProgressBar progressBar;
    ImageView back;

    @Override
    public void refreshContent(String titleNew,String newDetailSend, String imageUrl) {
        relatedTwoController = new RelatedTwoController();
        //Log.d("DetailNews", "Content refreshed");
        Utils.relatedTwoNewsList(this, relatedTwoController, recyclerView);
        title.setText(titleNew);
        title.setText(newDetailSend);
        Picasso.get()
                .load(imageUrl)
                .into(imageView);
        recyclerView.getAdapter().notifyDataSetChanged();


        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);
        nestedScrollView.smoothScrollTo(0, 0);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        String targetUuid = getIntent().getStringExtra("uuid");
       // String json_url = getIntent().getStringExtra("json_url");
        //burayı parse edip api call endpoint olarak vermeyi deneee !!

        progressBar = findViewById(R.id.progressBar);
         back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        context = findViewById(R.id.context);
        imageView = findViewById(R.id.newsImage);
        recyclerView = findViewById(R.id.recyclerView);
        webView = findViewById(R.id.webView);
        shareImage = findViewById(R.id.imageView4);
        textView = findViewById(R.id.textView);
        filtredController = new FiltredController();
        newsController = new NewsController();
        relatedController = new RelatedController();
        filteredRelatedController = new FilteredRelatedController();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showLoadingBar();


        adManager = new AdManager(this);
        adManager.initAd();

        back.setOnClickListener(v -> onBackPressed());

        View contentView = findViewById(R.id.main);
        Utils.applyWindowInsets(contentView);

        Utils.loadFilteredNews(this, targetUuid, filtredController, title, context, imageView);

        //ilişkili içerikler
        Utils.relatedNewsList(this, relatedController, recyclerView);
        Utils.loadFilteredNews(this, targetUuid, filteredRelatedController,webView,shareImage);

        hideLoadingBar();

        AdView adView = findViewById(R.id.adViewDetail);
        adViewMre = findViewById(R.id.adViewMREDetail);

        AdManager adManager = new AdManager(this);
        adManager.ShowBannerAd(adView);
        adManager.ShowMreBannerAd(adViewMre);

    }

    public void onBackPressed() {
        adManager.ShowInterstitialAd();
        super.onBackPressed();
    }

    private void showLoadingBar() {
        progressBar.setVisibility(View.VISIBLE);
        title.setVisibility(View.GONE);
        context.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
        shareImage.setVisibility(View.GONE);
    }


    private void hideLoadingBar() {
        progressBar.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        context.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
        shareImage.setVisibility(View.VISIBLE);
    }


}