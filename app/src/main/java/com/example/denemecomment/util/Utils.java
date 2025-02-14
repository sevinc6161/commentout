package com.example.denemecomment.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.denemecomment.adapter.DenemeAdapter;
import com.example.denemecomment.adapter.NewsListAdapter;
import com.example.denemecomment.adapter.RelatedAdapter;
import com.example.denemecomment.adapter.RelatedAdapterTwo;
import com.example.denemecomment.adapter.SliderAdapter;
import com.example.denemecomment.controller.FilteredRelatedController;
import com.example.denemecomment.controller.FiltredController;
import com.example.denemecomment.controller.NewsController;
import com.example.denemecomment.controller.RelatedController;
import com.example.denemecomment.controller.RelatedTwoController;
import com.example.denemecomment.model.DetailModel;
import com.example.denemecomment.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class Utils {




    //releated olanların denemesi




//Burası deneme olmazsa silmeyi unutma !!!!1

    public static void denemeLoad(Context context, NewsController newsController,
                                  RecyclerView recyclerView, Activity activity) {
        newsController.getNewsList(new NewsController.NewsListCallback() {
            @Override
            public void onSuccess(List<NewsItem> featured, List<NewsItem> items) {
                List<NewsItem> allNews = new ArrayList<>();
                allNews.addAll(items);
                DenemeAdapter adapter = new DenemeAdapter(context, allNews,activity);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //unutma !!

    public static void relatedNewsList(Context context, RelatedController newsController, RecyclerView recyclerView) {
        newsController.getNewsList(new RelatedController.NewsListCallback() {
            @Override
            public void onSuccess(DetailModel.Detail detail, DetailModel.Related related) {
                List<DetailModel.Related.Item> allNews = new ArrayList<>(related.getItems());
                RelatedAdapter adapter = new RelatedAdapter(context, allNews);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }


    //İLİŞKİLİ TIKLANINCA ÜST KISIM GÜNCELLEMEK İÇİN



    //unutma !!

    public static void loadFilteredNews(Context context, String targetUuid, FiltredController newsController,
                                        TextView title, TextView contentText, ImageView imageView) {
        newsController.getFilteredNews(targetUuid, new FiltredController.NewsListCallback() {
            @Override
            public void onSuccess(List<NewsItem> filteredNews) {
                if (filteredNews != null && !filteredNews.isEmpty()) {

                    NewsItem news = filteredNews.get(0);
                    title.setText(news.getTitle());
                    contentText.setText(news.getDetailMiniContent());
                    Glide.with(context)
                            .load(news.getMainImage().getUrl())
                            .into(imageView);
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Burada listeleme işlemi fonksiyonu

    public static void loadNewsList(Context context, NewsController newsController,
                                    RecyclerView recyclerView) {
        newsController.getNewsList(new NewsController.NewsListCallback() {
            @Override
            public void onSuccess(List<NewsItem> featured, List<NewsItem> items) {
                List<NewsItem> allNews = new ArrayList<>();
                allNews.addAll(items);
                NewsListAdapter adapter = new NewsListAdapter(context, allNews);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

        //Burada öne çıkarılanların fonksiyonu
    public static void loadNewsFeatureList(Context context, NewsController newsController,
                                    RecyclerView recyclerView, ViewPager2 viewPager2) {
        // newsController ile haberleri alıyoruz
        newsController.getNewsList(new NewsController.NewsListCallback() {
            @Override
            public void onSuccess(List<NewsItem> featured, List<NewsItem> items) {

                List<NewsItem> allFeatured = new ArrayList<>();
                allFeatured.addAll(featured);
                SliderAdapter sliderAdapter = new SliderAdapter(context, allFeatured);
                viewPager2.setAdapter(sliderAdapter);
            }

            @Override
            public void onError(String error) {

                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }



   //Burada safe area için gerekli olan fonksiyon var
    public static void applyWindowInsets(View view) {
        view.setOnApplyWindowInsetsListener((v, insets) -> {

            int topPadding = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    topPadding = insets.getInsets(WindowInsets.Type.systemBars()).top;
                }
            }

            int bottomPadding = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    bottomPadding = insets.getInsets(WindowInsets.Type.systemBars()).bottom;
                }
            }

            int leftPadding = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    leftPadding = insets.getInsets(WindowInsets.Type.systemBars()).left;
                }
            }

            int rightPadding = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    rightPadding = insets.getInsets(WindowInsets.Type.systemBars()).right;
                }
            }
            v.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

            return insets;
        });
    }



    //web view

    public static void loadFilteredNews(Context context, String targetUuid, FilteredRelatedController newsController
                                          , WebView contentWebView,ImageView shareImage) {
        newsController.getContentByUuid(targetUuid, new FilteredRelatedController.ContentCallback() {

            @Override
            public void onSuccess(String content, String shareUrl) {


                String htmlContent = "<html><body>" +
                        "<p>" + content + "</p>" +
                        "</body></html>";

                // HTML içeriğini WebView
                contentWebView.loadData(htmlContent, "text/html", "UTF-8");
                shareImage.setOnClickListener(v -> {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareUrl);
                    context.startActivity(Intent.createChooser(shareIntent, "Paylaş"));
                });
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

        //feed_two için
    public static void relatedTwoNewsList(Context context, RelatedTwoController newsController, RecyclerView recyclerView) {
        newsController.getNewsList(new RelatedTwoController.NewsListCallback() {
            @Override
            public void onSuccess(DetailModel.Detail detail, DetailModel.Related related) {
                List<DetailModel.Related.Item> allNews = new ArrayList<>(related.getItems());
                RelatedAdapterTwo adapter = new RelatedAdapterTwo(context, allNews);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
