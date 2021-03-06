package com.example.android.newsapp;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    public static final String LOG_TAG = NewsLoader.class.getSimpleName();
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground()");
        if (mUrl == null) {
            return null;
        }
        List<News> NewsList = QueryUtils.fetchNewsData(mUrl);
        if (NewsList.size() == 0) {
            Log.d(LOG_TAG, "news list is empty");
        } else {
            Log.d(LOG_TAG, NewsList.get(0).getTitle());
        }
        return NewsList;
    }
}