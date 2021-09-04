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
import android.widget.Toast;

import java.util.ArrayList;

public class AssignmentFragment extends Fragment {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> loadedAssignments;
    ArrayList<String> loadedDates;

    TextView assignmentsCount;

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
        assignmentsCount = view.findViewById(R.id.assignments_count);

        myDB = new MyDatabaseHelper(getActivity().getBaseContext());

        loadedAssignments = new ArrayList<>();
        loadedDates = new ArrayList<>();

        loadData();

        String count=  "You have " + loadedAssignments.size() + " pending assignment(s)!";


        assignmentsCount.setText(count);
        customAdapter = new CustomAdapter(getActivity().getBaseContext(), loadedAssignments, loadedDates);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));


        MainActivity.fragmentNumber = 2;

        return view;
    }


    void loadData() {

        Cursor cursor = myDB.loadAssignments();

        if (loadedAssignments.size() > 0)
            return;

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                loadedAssignments.add(cursor.getString(1));
                loadedDates.add(cursor.getString(2));

            }
        }
    }
}