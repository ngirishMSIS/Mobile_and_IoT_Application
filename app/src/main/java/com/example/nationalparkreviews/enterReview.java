package com.example.nationalparkreviews;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class enterReview extends AppCompatActivity {

    public String rate;
    public String rev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_review);

        EditText rating = findViewById(R.id.addRating);
        EditText review = findViewById(R.id.addReview);
        Button saveButton = findViewById(R.id.saveReview);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate = rating.getText().toString();
                rev = review.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("review", rev);
                intent.putExtra("rating",rate);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
