package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteSubjectActivity extends AppCompatActivity {

    Spinner course_numbers;
    Spinner days;
    Button delete_button;
    Button delete_all;
    Intent intent;

    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_subject);

        getSupportActionBar().hide();

        myDB = new MyDatabaseHelper(this);

        course_numbers = findViewById(R.id.number_spinner);
        days = findViewById(R.id.day_spinner);
        delete_button = findViewById(R.id.delete_button);
        delete_all = findViewById(R.id.delete_all);
        intent = new Intent(this, MainActivity.class);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.course_numbers, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        course_numbers.setAdapter(adapter);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> day_adapter = ArrayAdapter.createFromResource(this,
                R.array.days_of_week, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        days.setAdapter(day_adapter);


        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showConfirmDialog();

            }
        });


        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDeleteAllDialog();
            }
        });
    }


    void showConfirmDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Subject");
        builder.setMessage("Are you sure you want to delete the selected subject?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                int column = getColumn(days.getSelectedItem().toString().trim());
                int row = course_numbers.getSelectedItemPosition();

                String position = String.valueOf(column + 8 * row);
                myDB.deleteSubject(position);

                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }


    void showDeleteAllDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Subjects");
        builder.setMessage("Are you sure you want to delete all subjects in your time table?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                myDB.deleteAllSubjects();
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

    int getColumn(String selectedDay) {

        int column = 1;

        switch (selectedDay) {
            case "Sunday":
                column = 1;
                break;

            case "Monday":
                column = 2;
                break;

            case "Tuesday":
                column = 3;
                break;

            case "Wednesday":
                column = 4;
                break;

            case "Thursday":
                column = 5;
                break;

            case "Friday":
                column = 6;
                break;

            case "Saturday":
                column = 7;
                break;

        }
        return column;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(intent);
        finish();
    }
}