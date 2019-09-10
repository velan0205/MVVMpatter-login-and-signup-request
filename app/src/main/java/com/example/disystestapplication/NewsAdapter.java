package com.example.disystestapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.disystestapplication.model.NewsModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mCtx;
    private List<NewsModel> newsList;

    public NewsAdapter(Context mCtx, List<NewsModel> newsModelList) {
        this.mCtx = mCtx;
        this.newsList = newsModelList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel newsModel = newsList.get(position);
        holder.txtTitle.setText(newsModel.getStrTilte());
        holder.txtDescription.setText(newsModel.getStrDescription());
        holder.txtDate.setText(newsModel.getStrDate());
        Glide.with(mCtx).load(newsModel.getStrImage()).into(holder.img_icon);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView img_icon;
        TextView txtDescription, txtTitle, txtDate;

        NewsViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtDate = itemView.findViewById(R.id.txtDate);
            img_icon = itemView.findViewById(R.id.img_icon);
        }
    }
}
