package com.example.myapplication;


import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class TableFragment extends Fragment {


    private GridLayout gridLayout;
    private MyDatabaseHelper myDB;
    private ArrayList<String> loadedSubjects;
    public static ArrayList<Integer> loadedPositions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        myDB = new MyDatabaseHelper(getActivity().getBaseContext());

        loadedSubjects = new ArrayList<>();
        loadedPositions = new ArrayList<>();


        // To access a certain view in fragment
        // we need the big view itself which is represented
        // in the view variable above and access all the views
        // from within this view by view.findViewById ...

        gridLayout = view.findViewById(R.id.gridLayout);


        loadSubjects();
        displaySubjects();

        return view;
    }


    //Used to fetch data from the database
    // and inserting them in arrays
    void loadSubjects() {

        Cursor cursor = myDB.loadData();

        if (loadedSubjects.size() > 0)
            return;

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                loadedSubjects.add(cursor.getString(1));
                loadedPositions.add(Integer.valueOf(cursor.getString(2)));

            }
        }
    }

    // Used to display the loaded data
    //from arrays to the corresponding text views
    void displaySubjects() {

        TextView subjectCell;
        for (int i = 0; i < loadedPositions.size(); i++) {

            subjectCell = (TextView) gridLayout.getChildAt(loadedPositions.get(i));
            subjectCell.setText(loadedSubjects.get(i));
            subjectCell.setBackgroundColor(Color.WHITE);
        }
    }
}