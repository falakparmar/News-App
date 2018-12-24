package com.example.android.newsapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {

        super(context, 0, newsArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }
        TextView titleNews = (TextView) convertView.findViewById(R.id.title);
        TextView authorNews = (TextView) convertView.findViewById(R.id.author);
        titleNews.setText(news.getTitle());
        authorNews.setText(news.getAuthor());

        return convertView;
    }

}