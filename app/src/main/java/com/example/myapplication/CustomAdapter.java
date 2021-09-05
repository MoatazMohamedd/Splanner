package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    Context context;
    ArrayList<String> name, date;

    CustomAdapter(Context context, ArrayList<String> assignments, ArrayList<String> dueDates) {

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

    //onBind is used to set fields and texts etc..
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.assignmentText.setText(String.valueOf(name.get(position)));
        holder.dueDateText.setText(String.valueOf(date.get(position)));
        holder.deleteButton.setColorFilter(Color.RED);


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message;

                if (MainActivity.fragmentNumber == 2)
                    message = " assignment?";

                else message = " exam?";

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete \"" + name.get(holder.getAdapterPosition()) + "\"");
                builder.setMessage("Are you sure you want to delete this" + message);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MyDatabaseHelper db = new MyDatabaseHelper(context);

                        if (MainActivity.fragmentNumber == 2) {
                            db.deleteAssignment(String.valueOf(name.get(holder.getAdapterPosition())));

                            AssignmentFragment.loadedAssignments.remove(holder.getAdapterPosition());

                        } else {

                            db.deleteExam(String.valueOf(name.get(holder.getAdapterPosition())));

                            ExamFragment.loadedExams.remove(holder.getAdapterPosition());

                        }
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return name.size();
    }

    //Used to initialize variables and finding them

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView assignmentText, dueDateText;
        ImageButton deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            assignmentText = itemView.findViewById(R.id.assignment_name);
            dueDateText = itemView.findViewById(R.id.due_date);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

}
