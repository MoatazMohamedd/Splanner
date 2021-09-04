package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    Context context;
    ArrayList name, date;

    CustomAdapter(Context context, ArrayList assignments, ArrayList dueDates) {

       this.context = context;
       this.name = assignments;
       this.date = dueDates;

    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.assignmentText.setText(String.valueOf(name.get(position)));
        holder.dueDateText.setText(String.valueOf(date.get(position)));

    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView assignmentText, dueDateText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            assignmentText = itemView.findViewById(R.id.assignment_name);
            dueDateText = itemView.findViewById(R.id.due_date);
        }
    }
}
