package com.example.denemecomment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.denemecomment.DetailNews;
import com.example.denemecomment.R;
import com.example.denemecomment.controller.RelatedController;
import com.example.denemecomment.controller.RelatedTwoController;
import com.example.denemecomment.model.DetailModel;
import com.example.denemecomment.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelatedAdapterTwo extends RecyclerView.Adapter<RelatedAdapterTwo.NewsViewHolder> {
    private Context context;
    private List<DetailModel.Related.Item> newsItems;
    private RelatedTwoController relatedController;

    public RelatedAdapterTwo(Context context, List<DetailModel.Related.Item> newsItems) {
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
        DetailModel.Related.Item newsItem = newsItems.get(position);
        holder.title.setText(newsItem.getTitle());
        String uuid = String.valueOf(newsItem.getUuid());
        String imageUrl = newsItem.getMain_image().getUrl();
        Picasso.get()
                .load(imageUrl)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
        //BURADA FEED OLAN APİ CALL CALLBACK ÇAĞIR UNUTMA !
//    Toast.makeText(context, "uuid" + uuid, Toast.LENGTH_SHORT).show();


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
}
