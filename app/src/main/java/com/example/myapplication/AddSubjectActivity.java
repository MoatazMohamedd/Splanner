package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddSubjectActivity extends AppCompatActivity {

    private Intent intent;
    Button saveButton;
    private Button startTimeButton;
    private Button endTimeButton;
    private int startHour, startMinute, endHour, endMinute;
    private String AM_PM;
    private EditText subjectNameTextField;
    private Spinner daySpinner;


    private String selectedDay;
    private String totalText;

    String subjectName;
    String startTimeText;
    String endTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        getSupportActionBar().hide();

        //Assign Variables
        intent = new Intent(this, MainActivity.class);
        saveButton = findViewById(R.id.save_button);
        startTimeButton = findViewById(R.id.start_button);
        endTimeButton = findViewById(R.id.end_button);
        subjectNameTextField = findViewById(R.id.subject_name_field);
        daySpinner = findViewById(R.id.day_spinner);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_of_week, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        daySpinner.setAdapter(adapter);

        //When Save Button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                save();
            }
        });

        //When start time button is clicked
        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectStartTime();

            }
        });

        //When end time button is clicked
        endTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectEndTime();

            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void save() {
        //Assign input fields text to the string variables declared above
        addSubject();
        //Check if all the fields required for adding a subject are filled

        if (!checkRequiredFields()) {

            Toast.makeText(AddSubjectActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {


            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddSubjectActivity.this);
            myDatabaseHelper.addSubject(totalText, getPosition(selectedDay));


            startActivity(intent);
            finish();
        }
    }

    public void selectStartTime() {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                startHour = selectedHour;
                startMinute = selectedMinute;

                startHour = handleHourText(startHour);

                startTimeButton.setText(String.format("%02d:%02d " + AM_PM, startHour, startMinute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, startHour, startMinute, false);
        timePickerDialog.show();
    }

    public void selectEndTime() {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                endHour = selectedHour;
                endMinute = selectedMinute;

                endHour = handleHourText(endHour);

                endTimeButton.setText(String.format("%02d:%02d " + AM_PM, endHour, endMinute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, endHour, endMinute, false);
        timePickerDialog.show();
    }


    public int handleHourText(int hour) {
        if (hour < 12) {
            AM_PM = "AM";

            if (hour == 0)
                hour = 12;
        } else {
            AM_PM = "PM";
            if (hour > 12)
                hour -= 12;
        }
        return hour;
    }

    public boolean checkRequiredFields() {
        if (startHour < 1 || endHour < 1 || subjectNameTextField.getText().toString().trim().isEmpty()) {

            return false;
        }
        return true;
    }


    public void addSubject() {
        subjectName = subjectNameTextField.getText().toString().trim();
        startTimeText = startTimeButton.getText().toString().trim();
        endTimeText = endTimeButton.getText().toString().trim();
        selectedDay = daySpinner.getSelectedItem().toString().trim();


        totalText = subjectName + "\n\n" + startTimeText + "\n" + " | \n" + endTimeText;


    }

    public int getPosition(String selectedDay) {
        int column = getColumn(selectedDay);


        if (TableFragment.loadedPositions.size() == 0)
            return column;

        for (int i = 0; i < TableFragment.loadedPositions.size(); i++) {
            if (TableFragment.loadedPositions.get(i) == column) {
                column += 8;
            }
        }
        return column;

    }

    public int getColumn(String selectedDay) {

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
}