package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class AssignmentFragment extends Fragment {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    public static ArrayList<String> loadedAssignments;
    ArrayList<String> loadedDates;


    CustomAdapter customAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);


        recyclerView = view.findViewById(R.id.recycler_view);

        myDB = new MyDatabaseHelper(getActivity());

        loadedAssignments = new ArrayList<>();
        loadedDates = new ArrayList<>();

        loadData();

        TextView hintText = view.findViewById(R.id.hint);

        if (loadedAssignments.size() > 0)
            hintText.setVisibility(View.INVISIBLE);

        else hintText.setVisibility(View.VISIBLE);

            customAdapter = new CustomAdapter(getActivity(), loadedAssignments, loadedDates);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        MainActivity.fragmentNumber = 2;

        return view;
    }


    void loadData() {

        Cursor cursor = myDB.loadAssignments();

        if (loadedAssignments.size() > 0)
            return;


        while (cursor.moveToNext()) {

            loadedAssignments.add(cursor.getString(1));
            loadedDates.add(cursor.getString(2));

        }
    }
}
