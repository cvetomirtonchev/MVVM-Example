package com.example.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvm.adapters.NewsAdapter;
import com.example.mvvm.model.NewsArticle;
import com.example.mvvm.networking.BaseApiResponse;
import com.example.mvvm.viewmodels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class MainActivity extends AppCompatActivity {
    private ContentLoadingProgressBar mProgressBar;
    private ArrayList<NewsArticle> mArticlesList = new ArrayList<>();
    private NewsAdapter mNewsAdapter;
    private RecyclerView mRecyclerView;
    private NewsViewModel mNewsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progressBar);
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mRecyclerView = findViewById(R.id.rvNews);

        setupRecyclerView();
        initNewsDataObserver();
        getNews();
    }

    private void initNewsDataObserver() {
        mNewsViewModel.getNewsMutableLiveData().observe(this, newsResponse -> {
            mProgressBar.setVisibility(View.INVISIBLE);
            if (newsResponse.getResponseStatus() == BaseApiResponse.STATUS_OK) {
                List<NewsArticle> newsArticles = newsResponse.getResponseData().getArticles();
                mArticlesList.addAll(newsArticles);
                mNewsAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupRecyclerView() {
        if (mNewsAdapter == null) {
            mNewsAdapter = new NewsAdapter(MainActivity.this, mArticlesList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mNewsAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setNestedScrollingEnabled(true);
        } else {
            mNewsAdapter.notifyDataSetChanged();
        }
    }

    private void getNews() {
        mProgressBar.setVisibility(View.VISIBLE);
        mNewsViewModel.getNews();
    }
}
