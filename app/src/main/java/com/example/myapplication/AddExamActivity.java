package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Calendar;


public class AddExamActivity extends AppCompatActivity {

    private Intent intent;
    private Button dateButton;
    private EditText examInput;


    private Calendar calendar;


    int selectedYear, selectedDay, selectedMonth;
    String examName, totalDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        getSupportActionBar().hide();
        calendar = Calendar.getInstance();

        intent = new Intent(this, MainActivity.class);

        dateButton = findViewById(R.id.date_button);
        Button saveButton = findViewById(R.id.save_button);
        examInput = findViewById(R.id.exam_name_field);


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
                    Toast.makeText(AddExamActivity.this, "Choose a day in the future", Toast.LENGTH_SHORT).show();

                    // Minus 2 because when I get the day of week it chooses the wrong day
                    // if today's day is 3 then it shows 5

                else if (selectedYear == calendar.get(Calendar.YEAR) && selectedDay < calendar.get(Calendar.DAY_OF_WEEK - 2))
                    Toast.makeText(AddExamActivity.this,"Choose a day in the future", Toast.LENGTH_SHORT).show();

                else if (selectedYear == calendar.get(Calendar.YEAR) && selectedMonth < calendar.get(Calendar.MONTH))
                    Toast.makeText(AddExamActivity.this,"Choose a day in the future", Toast.LENGTH_SHORT).show();

                else if (checkRequiredFields()) {
                    examName = examInput.getText().toString().trim();

                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddExamActivity.this);
                    myDB.addExam(examName, "Due Date: " + totalDate);

                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(AddExamActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();


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

                totalDate = day + "/" + (month + 1) + "/" + year;

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
        if (examInput.getText().toString().equals("")) {

            return false;
        }
        return true;
    }

}