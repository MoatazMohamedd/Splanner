package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddAssignmentActivity extends AppCompatActivity {

    private Intent intent;
    private Button dateButton;
    private Button saveButton;
    private EditText assignmentInput;


    private Calendar calendar;


    int selectedYear, selectedDay, selectedMonth;
    String assignmentName, totalDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        getSupportActionBar().hide();
        calendar = Calendar.getInstance();

        intent = new Intent(this, MainActivity.class);

        dateButton = findViewById(R.id.date_button);
        saveButton = findViewById(R.id.save_button);
        assignmentInput = findViewById(R.id.assignment_name_field);


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseDate();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (selectedYear < calendar.get(Calendar.YEAR))
                    Toast.makeText(AddAssignmentActivity.this, "Choose a day in the future", Toast.LENGTH_SHORT).show();

                    // Minus 2 because when I get the day of week it chooses the wrong day
                    // if today's day is 3 then it shows 5

                else if (selectedYear == calendar.get(Calendar.YEAR) && selectedDay < calendar.get(Calendar.DAY_OF_WEEK - 2))
                    Toast.makeText(AddAssignmentActivity.this, "Choose a day in the future", Toast.LENGTH_SHORT).show();

                else if (selectedYear == calendar.get(Calendar.YEAR) && selectedMonth < calendar.get(Calendar.MONTH))
                    Toast.makeText(AddAssignmentActivity.this, "Choose a day in the future", Toast.LENGTH_SHORT).show();

                else if (checkRequiredFields()) {
                    assignmentName = assignmentInput.getText().toString().trim();

                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddAssignmentActivity.this);
                    myDB.addAssignment(assignmentName, "Due Date: " + totalDate);

                    startActivity(intent);
                    finish();
                }

                    else
                        Toast.makeText(AddAssignmentActivity.this, "Please fill all fields",Toast.LENGTH_SHORT).show();


            }
        });


    }


    public void chooseDate() {

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {


                selectedDay = day;
                selectedMonth = month;
                selectedYear = year;

                totalDate = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);

                dateButton.setText(totalDate);


            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_WEEK - 2));

        datePickerDialog.show();
    }

    public void onBackPressed() {

        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public boolean checkRequiredFields() {
        if (assignmentInput.getText().toString().equals("")) {

            return false;
        }
        return true;
    }

}