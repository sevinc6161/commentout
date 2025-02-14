package com.example.denemecomment.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.denemecomment.R;
import com.example.denemecomment.model.NewsItem;
import com.squareup.picasso.Picasso;
import java.util.List;



public class FiltredAdapter extends RecyclerView.Adapter<FiltredAdapter.NewsViewHolder> {
    private Context context;
    private List<NewsItem> newsItems;

    public FiltredAdapter(Context context, List<NewsItem> newsItems) {
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
        holder.title.setText(newsItem.getTitle() );


        String imageUrl = newsItem.getMainImage().getUrl();
        Uri imageUri = Uri.parse(imageUrl);
        Picasso.get()
                .load(imageUri)
                .into(holder.imageView);





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
}
