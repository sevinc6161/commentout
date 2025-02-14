// RelatedAdapter.java
package com.example.denemecomment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.denemecomment.R;
import com.example.denemecomment.controller.RelatedController;
import com.example.denemecomment.model.DetailModel;
import com.example.denemecomment.util.DetailNewsCallback;
import com.example.denemecomment.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

//BURADA DETAİL SAYFASINDA RELATED OLANI BULUYOR
public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.NewsViewHolder> {
    private Context context;
    private List<DetailModel.Related.Item> newsItems;
    private RelatedController relatedController;

    public RelatedAdapter(Context context, List<DetailModel.Related.Item> newsItems) {
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
        String titleSend = newsItem.getTitle();
        String newDetailSend = newsItem.getDetail_mini_content();
        String imageUrl = newsItem.getMain_image().getUrl();
        Picasso.get()
                .load(imageUrl)
                .into(holder.imageView);

        //call back yapıyor ve liste olanı yeniliyor burada bi call back
        // daha eklenecek üstteki haber listelensin diye
        holder.itemView.setOnClickListener(v -> {
            //((DetailNewsCallback) context).refreshContent();
            onItemClick(titleSend,newDetailSend,imageUrl);
        });
        holder.imageView.setOnClickListener(v -> {
            //((DetailNewsCallback) context).refreshContent();
            onItemClick(titleSend,newDetailSend,imageUrl);
        });
        //burada image view için ekle setOnClick fonksiyon yap unutma!
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

    public void onItemClick(String titleSend, String newDetailSend,String imageUrl ) {
        ((DetailNewsCallback) context).refreshContent(titleSend,newDetailSend,imageUrl);
    }

}
