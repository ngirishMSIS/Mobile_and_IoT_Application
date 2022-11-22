package com.example.nationalparkreviews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Results extends AppCompatActivity {
    TextView user, state, rating;
    Button addButton;
    ListView listView;
    public String username;
    private ArrayList<Review> reviewList;
    public int LAUNCH_SECOND_ACTIVITY = 1;
    public ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        reviewList = new ArrayList<>();
                        //Resources res = getResources();
                        Intent data = result.getData();
                        if (data==null){
                            Toast.makeText(Results.this, "intent is null", Toast.LENGTH_SHORT).show();
                        }
                        String rat = data.getStringExtra("rating");
                        String rev= data.getStringExtra("review");

                        //populateReviewList(usernames, ratings, reviews);
                        Review newly = new Review(username, rat, rev);

                        reviewList.add(newly);

                        reviewAdapter adapter = new reviewAdapter(Results.this, R.layout.list_item, reviewList);

                        listView = findViewById(R.id.listView);

                        listView.setAdapter(adapter);
                    }
                }
            });

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        user = findViewById(R.id.usernameResult);
        state = findViewById(R.id.stateResult);

        Intent intent = getIntent();
        username = intent.getStringExtra("Username");
        String welcome = "Welcome " + username + "!";
        String parkName = intent.getStringExtra("Park") + ", " + intent.getStringExtra("State");
        user.setText(welcome);
        state.setText(parkName);

        rating = findViewById(R.id.rating);
        String rate = "Rating: 4.5/5";
        rating.setText(rate);

        reviewList = new ArrayList<>();
        Resources res = getResources();
        String[] usernames = res.getStringArray(R.array.usernames);
        String[] ratings = res.getStringArray(R.array.ratings);
        String[] reviews = res.getStringArray(R.array.reviews);

        populateReviewList(usernames, ratings, reviews);

        reviewAdapter adapter = new reviewAdapter(Results.this, R.layout.list_item, reviewList);

        listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Results.this, "To be completed when Integrating Firebase DB", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext()
                        , enterReview.class);
                someActivityResultLauncher.launch(intent);
            }
        });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                reviewList = new ArrayList<>();
                //Resources res = getResources();
                Intent intent = getIntent();
                String username = intent.getStringExtra("Username");
                String rating = data.getStringExtra("rating");
                String review = data.getStringExtra("review");

                //populateReviewList(usernames, ratings, reviews);

                reviewList.add(new Review(username,rating,review));

                reviewAdapter adapter = new reviewAdapter(Results.this, R.layout.list_item, reviewList);

                listView = findViewById(R.id.listView);

                listView.setAdapter(adapter);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                    // Write your code if there's no result
            }
        }
    }*/

    public void populateReviewList(String[] usernames,String[] ratings, String[] reviews ){
        reviewList.add(new Review(usernames[0],ratings[0],reviews[0]));
        reviewList.add(new Review(usernames[1],ratings[1],reviews[1]));
        reviewList.add(new Review(usernames[2],ratings[2],reviews[2]));
    }
}
