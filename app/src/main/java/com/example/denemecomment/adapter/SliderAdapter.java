package com.example.denemecomment.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.denemecomment.DetailNews;
import com.example.denemecomment.R;
import com.example.denemecomment.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;


//BURADA SCROLL OLAN ÖNE ÇIKANLARI RECYCLEVİEW !!
public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.NewsViewHolder> {
    private Context context;
    private List<NewsItem> newsItems;

    public SliderAdapter(Context context, List<NewsItem> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsItem newsItem = newsItems.get(position);

        String uuid = newsItem.getUuid();
        holder.title.setText(newsItem.getTitle());

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
            title = itemView.findViewById(R.id.featureText);
            imageView = itemView.findViewById(R.id.featureImage);
        }
    }

    public void onItemClick(String uuid) {

        Intent intent = new Intent(context, DetailNews.class);
        intent.putExtra("uuid", uuid);
        context.startActivity(intent);
    }
}
