package com.example.nationalparkreviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ListOfStates extends AppCompatActivity{
    Spinner states_spinner, parks_spinner;
    Button review;
    public String username;
    public String stateVal, parkVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_parks);

        Intent intent = getIntent();
        username = intent.getStringExtra("Username");

        states_spinner = findViewById(R.id.statesSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        states_spinner.setAdapter(adapter);

        Map<String, Integer> spinner2_values = new HashMap<>();
        spinner2_values.put("Alaska",R.array.alaska);
        spinner2_values.put("American Samoa",R.array.american_samoa);
        spinner2_values.put("Arizona",R.array.arizona);
        spinner2_values.put("Arkansas",R.array.arkansas);
        spinner2_values.put("California",R.array.california);
        spinner2_values.put("Colorado",R.array.colorado);
        spinner2_values.put("Florida",R.array.florida);
        spinner2_values.put("Hawaii",R.array.hawaii);
        spinner2_values.put("Idaho",R.array.idaho);
        spinner2_values.put("Kentucky",R.array.kentucky);
        spinner2_values.put("Indiana",R.array.indiana);
        spinner2_values.put("Maine",R.array.maine);
        spinner2_values.put("Michigan",R.array.michigan);
        spinner2_values.put("Minnesota",R.array.minnesota);
        spinner2_values.put("Missouri",R.array.missouri);
        spinner2_values.put("Montana",R.array.montana);
        spinner2_values.put("Nevada",R.array.nevada);
        spinner2_values.put("New Mexico",R.array.new_mexico);
        spinner2_values.put("North Dakota",R.array.north_dakota);
        spinner2_values.put("North Carolina",R.array.north_carolina);
        spinner2_values.put("Ohio",R.array.ohio);
        spinner2_values.put("Oregon",R.array.oregon);
        spinner2_values.put("South Carolina",R.array.south_carolina);
        spinner2_values.put("South Dakota",R.array.south_dakota);
        spinner2_values.put("Tennessee",R.array.tennessee);
        spinner2_values.put("Texas",R.array.texas);
        spinner2_values.put("Utah",R.array.utah);
        spinner2_values.put("Virgin Islands",R.array.virgin_islands);
        spinner2_values.put("Virginia",R.array.virginia);
        spinner2_values.put("Washington",R.array.washington);
        spinner2_values.put("West Virginia",R.array.west_virginia);
        spinner2_values.put("Wyoming",R.array.wyoming);

        states_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                stateVal = states_spinner.getSelectedItem().toString();
                parks_spinner= findViewById(R.id.parksSpinner);

                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getApplicationContext(),
                        spinner2_values.get(stateVal), android.R.layout.simple_spinner_item);

                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                parks_spinner.setAdapter(adapter2);

                parks_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        parkVal = parks_spinner.getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        review = (Button) findViewById(R.id.review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResults();
            }
        });
    }

    public void openResults() {
        Intent intent = new Intent(this, Results.class);
        intent.putExtra("Username",username);
        intent.putExtra("State", stateVal);
        intent.putExtra("Park",parkVal);
        startActivity(intent);
    }
}
