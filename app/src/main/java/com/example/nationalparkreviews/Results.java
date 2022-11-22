package com.example.nationalparkreviews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Results extends AppCompatActivity {
    TextView user, state, rating;
    Button addButton;
    ListView listView;
    public String username, rate;
    private ArrayList<Review> reviewList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


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
        String st = intent.getStringExtra("State").toLowerCase().replace(" ", "_").replace("-", "_").trim();
        String temp = intent.getStringExtra("Park");
        String park_temp = temp.substring(0, temp.length() - 14).toLowerCase().replace(" ", "_").replace("-", "_").trim();
        System.out.println(park_temp);
        db.collection(st)
                .whereEqualTo(FieldPath.documentId(), park_temp)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                rate = "Rating: " + document.getData().get("rating");
                                System.out.println(rate);
                                rating = findViewById(R.id.rating);
                                rating.setText(rate);
                            }
                        }
                    }
                });

        username = intent.getStringExtra("Username");
        String welcome = "Welcome " + username + "!";
        String parkName = intent.getStringExtra("Park") + ", " + intent.getStringExtra("State");
        user.setText(welcome);
        state.setText(parkName);



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

    public void populateReviewList(String[] usernames,String[] ratings, String[] reviews ){
        reviewList.add(new Review(usernames[0],ratings[0],reviews[0]));
        reviewList.add(new Review(usernames[1],ratings[1],reviews[1]));
        reviewList.add(new Review(usernames[2],ratings[2],reviews[2]));
    }
}
