package com.example.disystestapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.disystestapplication.NewsAdapter;
import com.example.disystestapplication.R;
import com.example.disystestapplication.ViewModel.NewsViewModel;
import com.example.disystestapplication.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class DataDisplayActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recy_data;
    private NewsAdapter newsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);

        recy_data = findViewById(R.id.recy_data);
        recy_data.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.swipetorefresh);

        progressBar.setVisibility(View.VISIBLE);

        observable();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * Getting observable data from viewModel
     */
    private void observable() {
        NewsViewModel model = ViewModelProviders.of(this).get(NewsViewModel.class);
        model.getNews().observe(this, new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(@Nullable List<NewsModel> newsList) {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                newsAdapter = new NewsAdapter(DataDisplayActivity.this, newsList);
                recy_data.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                recy_data.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onRefresh() {
        observable();
    }
}
