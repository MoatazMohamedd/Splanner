package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class AddAssignmentActivity extends AppCompatActivity {

    private Intent intent;
    private Button dateButton;

    private Calendar calendar;

    int selectedYear, selectedDay, selectedMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        getSupportActionBar().hide();
        calendar = Calendar.getInstance();

        intent = new Intent(this, MainActivity.class);

        dateButton = findViewById(R.id.date_button);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectStartTime();
            }
        });


    }


    public void selectStartTime() {

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {


                dateButton.setText(String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));


            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_WEEK));

        datePickerDialog.show();
    }

    public void onBackPressed() {

        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}