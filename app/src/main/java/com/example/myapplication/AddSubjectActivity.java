package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddSubjectActivity extends AppCompatActivity {

    private Intent intent;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        getSupportActionBar().hide();

        //Assign Variables
        intent= new Intent(this,MainActivity.class);
        saveButton= findViewById(R.id.save_button);

        //Creating Dropdown menu (Spinner) for days
        Spinner spinner = (Spinner) findViewById(R.id.day_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.daysofweek, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //When Save Button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void save()
    {
        startActivity(intent);
        finish();
    }
}