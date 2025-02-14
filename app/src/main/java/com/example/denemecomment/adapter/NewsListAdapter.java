package com.example.denemecomment.adapter;


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
import com.example.denemecomment.controller.NewsController;
import com.example.denemecomment.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;


//BUNU KULLANMIYORUM SİL BURADA REKLAMSIZ OLAN LİSTELEME YAPTIM SİL !!!!
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {
    private Context context;
    private List<NewsItem> newsItems;


    private NewsController newsController;

    public NewsListAdapter(Context context, List<NewsItem> newsItems ) {
        this.context = context;
        this.newsItems = newsItems;

    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsItem newsItem = newsItems.get(position);

        holder.title.setText(newsItem.getTitle());
        String uuid = newsItem.getUuid();

        String imageUrl = newsItem.getMainImage().getUrl();
        Uri imageUri = Uri.parse(imageUrl);
        Picasso.get()
                .load(imageUri)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            onItemClick(uuid);
        });
        holder.imageView.setOnClickListener(v -> {
            onItemClick(uuid);
        });


    }


    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    public void onItemClick(String uuid) {

        Intent intent = new Intent(context, DetailNews.class);
        intent.putExtra("uuid", uuid);

        context.startActivity(intent);
    }

}
