package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExamFragment extends Fragment {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    public static ArrayList<String> loadedExams;
    ArrayList<String> loadedDates;

    FloatingActionButton fab;
    TextView hintText;

    CustomAdapter customAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exam, container, false);


        recyclerView = view.findViewById(R.id.recycler_view);
        fab = view.findViewById(R.id.delete_all);

        myDB = new MyDatabaseHelper(getActivity());

        loadedExams = new ArrayList<>();
        loadedDates = new ArrayList<>();

        loadData();

        hintText = view.findViewById(R.id.hint);

        if (loadedExams.size() > 0)
            hintText.setVisibility(View.INVISIBLE);

        else hintText.setVisibility(View.VISIBLE);

        customAdapter = new CustomAdapter(getActivity(), loadedExams, loadedDates);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        MainActivity.fragmentNumber = 3;

        fab.setColorFilter(Color.RED);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showConfirmDialog();
            }
        });

        return view;
    }


    void loadData() {

        Cursor cursor = myDB.loadExams();

        if (loadedExams.size() > 0)
            return;


        while (cursor.moveToNext()) {

            loadedExams.add(cursor.getString(1));
            loadedDates.add(cursor.getString(2));

        }
    }

    void showConfirmDialog() {

        String message;

        if (MainActivity.fragmentNumber == 2)
            message = " assignments?";

        else message = " exams?";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure you want to delete all" + message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (loadedExams.size() > 0) {

                    myDB.deleteAllExams();
                    loadedExams.clear();
                    loadedDates.clear();
                    customAdapter.notifyDataSetChanged();
                    hintText.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "All exams deleted", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getActivity(), "No exams available to delete!", Toast.LENGTH_SHORT).show();
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
