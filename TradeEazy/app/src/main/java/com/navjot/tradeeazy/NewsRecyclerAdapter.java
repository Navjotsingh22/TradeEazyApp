package com.navjot.tradeeazy;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.A;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {

    private final List<Article> articleList;

    public NewsRecyclerAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articleList.get(position);

        // Check if article name or source is "[Removed]" This was problem with API
        if (!article.getTitle().equals("[Removed]") && !article.getSource().getName().equals("[Removed]")) {
            holder.titleTextView.setText(article.getTitle());
            holder.sourceTextView.setText(article.getSource().getName());
            Picasso.with(holder.itemView.getContext())
                    .load(article.getUrlToImage())
                    .into(holder.imageView);


        } else {
            // If article name or source is "[Removed]", hide the item
            holder.imageView.setImageResource(R.drawable.ic_launcher);
            holder.titleTextView.setText("Powered by NewsAPI");
            holder.sourceTextView.setText("Rate Us");
        }
    }

    void  updateData(List<Article> data){
        articleList.clear();
        articleList.addAll(data);
}
    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, sourceTextView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.article_title);
            sourceTextView = itemView.findViewById(R.id.article_source);
            imageView = itemView.findViewById(R.id.article_image_view);

        }


    }
}