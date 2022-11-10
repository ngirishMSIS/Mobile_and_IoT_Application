package com.example.nationalparkreviews;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {
    TextView user, state, park;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        user = findViewById(R.id.usernameResult);
        state = findViewById(R.id.stateResult);
        park = findViewById(R.id.parkResult);

        Intent intent = getIntent();
        user.setText(intent.getStringExtra("Username"));
        state.setText(intent.getStringExtra("State"));
        park.setText(intent.getStringExtra("Park"));

    }
}
