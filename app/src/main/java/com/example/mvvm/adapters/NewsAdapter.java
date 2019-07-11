package com.example.mvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvm.R;
import com.example.mvvm.model.NewsArticle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final Context mContext;
    private final ArrayList<NewsArticle> mArticles;

    public NewsAdapter(Context context, ArrayList<NewsArticle> articles) {
        this.mContext = context;
        this.mArticles = articles;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        holder.tvName.setText(mArticles.get(position).getTitle());
        holder.tvDescription.setText(mArticles.get(position).getDescription());
        Picasso.get().load(mArticles.get(position).getImageUrl()).into(holder.ivNews);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvDescription;
        private final ImageView ivNews;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDesCription);
            ivNews = itemView.findViewById(R.id.ivNews);
        }
    }
}
