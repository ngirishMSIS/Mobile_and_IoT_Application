
package com.example.nationalparkreviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private Button login;
    public String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username1 = findViewById(R.id.username);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = username1.getText().toString().trim();
                if (username.equals("")){
                    Toast.makeText(MainActivity.this, "Username is missing", Toast.LENGTH_SHORT).show();
                }
                else {
                    openListOfParks();
                }
            }
        });
    }

    public void openListOfParks() {
        Intent intent = new Intent(this, ListOfStates.class);
        intent.putExtra("Username",username);
        startActivity(intent);
    }
}