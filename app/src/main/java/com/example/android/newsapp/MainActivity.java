package com.example.android.newsapp;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private static String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    private int loader_id = 1;
    private NewsAdapter mAdapter = null;
    private TextView mEmptyTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsList = (ListView) findViewById(R.id.newsListView);
        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsList.setAdapter(mAdapter);

        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getWebUrl());
                if (currentNews.getWebUrl() == null || TextUtils.isEmpty(currentNews.getWebUrl())) {

                    Toast.makeText(MainActivity.this, MainActivity.this.getResources().getString(R.string.no_link), Toast.LENGTH_LONG).show();

                } else {
                    Intent newsIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                    startActivity(newsIntent);
                }
            }
        });
        LoaderManager loaderManager = null;
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyTextView.setVisibility(View.GONE);
            loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(loader_id, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.INVISIBLE);
            mEmptyTextView.setText(getString(R.string.no_connection));
        }
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        mEmptyTextView.setText(getString(R.string.no_stories));
        mAdapter.clear();
        ProgressBar progress = (ProgressBar) findViewById(R.id.loading_indicator);
        progress.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mAdapter.clear();
    }

}