package com.example.denemecomment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denemecomment.DetailNews;
import com.example.denemecomment.R;
import com.example.denemecomment.controller.AdManager;
import com.example.denemecomment.model.NewsItem;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import java.util.List;


//BURADA MAİNDEKİ LİSTELEME RECYCLEVİEW VAR REKLAM GÖSTERİYOR !!!
public class DenemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<NewsItem> newsItems;
    private Activity activity;

    private static final int VIEW_TYPE_NEWS = 0;
    private static final int VIEW_TYPE_AD = 1;

    private int adCounter = 0;

    public DenemeAdapter(Context context, List<NewsItem> newsItems, Activity activity) {
        this.context = context;
        this.newsItems = newsItems;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {

        if (adCounter == 3) {
            adCounter = 0;
            return VIEW_TYPE_AD;
        }
        adCounter++;
        return VIEW_TYPE_NEWS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_AD) {

            View view = LayoutInflater.from(context).inflate(R.layout.ad_layout, parent, false);
            return new AdViewHolder(view);
        } else {

            View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
            return new NewsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdViewHolder) {

            AdViewHolder adViewHolder = (AdViewHolder) holder;
            AdManager adManager = new AdManager(activity);
            adManager.ShowMreBannerAd(adViewHolder.adView);
        } else if (holder instanceof NewsViewHolder) {

            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            NewsItem newsItem = newsItems.get(position);

            newsViewHolder.title.setText(newsItem.getTitle());
            String uuid = newsItem.getUuid();
            String json_url = newsItem.getJson_url();
            String imageUrl = newsItem.getMainImage().getUrl();
            Uri imageUri = Uri.parse(imageUrl);


            Picasso.get().load(imageUri).into(newsViewHolder.imageView);

         //   newsViewHolder.adView.setVisibility(View.GONE);


            newsViewHolder.itemView.setOnClickListener(v -> {
                onItemClick(uuid,json_url);
            });
            newsViewHolder.imageView.setOnClickListener(v -> {
                onItemClick(uuid,json_url);
            });
        }
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }


    public void onItemClick(String uuid,String json_url) {
        Intent intent = new Intent(context, DetailNews.class);
        intent.putExtra("uuid", uuid);
        intent.putExtra("json_url",json_url );
        context.startActivity(intent);
    }

    // Haber ViewHolder
    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        AdView adView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
            adView = itemView.findViewById(R.id.item_ad);
        }
    }


    public class AdViewHolder extends RecyclerView.ViewHolder {
        AdView adView;

        public AdViewHolder(View itemView) {
            super(itemView);
            adView = itemView.findViewById(R.id.item_ad);
        }
    }
}
