package com.example.myapplication;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.TimePicker;


public class TableFragment extends Fragment {


    private GridLayout gridLayout;
    private TextView   subjectCell;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_table, container, false);

        // To access a certain view in fragment
        // we need the big view itself which is represented
        // in the view variable above and access all the views
        // from within this view by view.findViewById ...

        gridLayout= view.findViewById(R.id.gridLayout);
        subjectCell= (TextView) gridLayout.getChildAt(1);

       if(subjectCell.getText().equals(""))
        {
            subjectCell.setBackgroundColor(Color.GREEN);
        }

       loadSubjects();

        return  view;
    }

    public void loadSubjects()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        subjectCell.setText(sharedPreferences.getString("cell"+1,""));
    }

    public void delete()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cell1","").apply();
        loadSubjects();
    }

    }