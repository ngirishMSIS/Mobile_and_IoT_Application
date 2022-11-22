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

        EditText ra = findViewById(R.id.addRating);
        EditText re = findViewById(R.id.addReview);
        rate = ra.getText().toString();
        rev = re.getText().toString();
        Button saveButton = findViewById(R.id.saveReview);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("review", rev);
                intent.putExtra("rating",rate);
                TextView title = findViewById(R.id.titleReview);
                title.setText(rev);
                setResult(RESULT_OK, intent);
                //finish();
            }
        });
    }

}
