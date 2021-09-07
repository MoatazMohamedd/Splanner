package com.example.myapplication;


import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class TableFragment extends Fragment {


    private GridLayout gridLayout;

    ArrayList<String> loadedSubjects;
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


        // To access a certain view in fragment
        // we need the big view itself which is represented
        // in the view variable above and access all the views
        // from within this view by view.findViewById ...

        gridLayout = view.findViewById(R.id.gridLayout);

        loadedSubjects = new ArrayList<>();
        loadedPositions = new ArrayList<>();

        if (loadedPositions.size() == 0) {

            loadSubjects();
        }

        displaySubjects();


        MainActivity.fragmentNumber = 1;
        return view;
    }


    //Used to fetch data from the database
    // and inserting them in arrays
    void loadSubjects() {

        MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());
        Cursor cursor = myDB.loadSubjects();


        while (cursor.moveToNext()) {

            loadedSubjects.add(cursor.getString(1));
            loadedPositions.add(Integer.valueOf(cursor.getString(2)));

        }

    }

    // Used to display the loaded data
    //from arrays to the corresponding text views
    void displaySubjects() {

        TextView subjectCell;
        for (int i = 0; i < loadedPositions.size(); i++) {

            subjectCell = (TextView) gridLayout.getChildAt(loadedPositions.get(i));
            subjectCell.setText(loadedSubjects.get(i));
            subjectCell.setBackgroundResource(R.drawable.round_button);
        }
    }
}