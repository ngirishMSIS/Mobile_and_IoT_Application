package com.example.nationalparkreviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class reviewAdapter extends ArrayAdapter<Review> {
    private ArrayList<Review> reviewsList;
    private Context mcontext;
    int mResource;

    public reviewAdapter(@NonNull Context context, int resource, ArrayList<Review> reviewList){
        super(context,resource,reviewList);
        this.reviewsList = reviewList;
        mcontext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        //details of review
        convertView = LayoutInflater.from(mcontext).inflate(mResource, parent, false);

        TextView username = convertView.findViewById(R.id.list_username);
        TextView rating = convertView.findViewById(R.id.list_rating);
        TextView review = convertView.findViewById(R.id.list_review);

        username.setText(reviewsList.get(position).getUsername());
        rating.setText("Rating: "+reviewsList.get(position).getRating());
        review.setText(reviewsList.get(position).getReview());

        return convertView;
    }
}
