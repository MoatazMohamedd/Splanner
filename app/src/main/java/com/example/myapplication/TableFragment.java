package com.example.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class TableFragment extends Fragment {


    private GridLayout gridLayout;

    ArrayList<String> loadedSubjects;
    public static ArrayList<Integer> loadedPositions;

    Intent intent;

    FloatingActionButton fab;

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

        fab = view.findViewById(R.id.fab);
        fab.setColorFilter(Color.RED);
        gridLayout = view.findViewById(R.id.gridLayout);

        intent = new Intent(getActivity(), DeleteSubjectActivity.class);

        loadedSubjects = new ArrayList<>();
        loadedPositions = new ArrayList<>();


        loadSubjects();


        displaySubjects();


        MainActivity.fragmentNumber = 1;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showConfirmDialog();

            }
        });


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


    void showConfirmDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Subject");
        builder.setMessage("Please keep in mind the day and number of the subject you want to delete");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (loadedSubjects.size() == 0)
                    Toast.makeText(getActivity(), "No subjects available to delete!", Toast.LENGTH_SHORT).show();

                else {


                    startActivity(intent);
                    getActivity().finish();
                }


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }
}