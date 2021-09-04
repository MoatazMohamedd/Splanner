package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public Context context;
    private static final String DATABASE_NAME = "Splanner.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME1 = "my_subjects";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_POSITION = "position";


    private static final String TABLE_NAME2 = "my_assignments";
    private static final String COLUMN_ASSIGNMENT = "assignment";
    private static final String COLUMN_DATE = "date";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String subjectQuery = "CREATE TABLE " + TABLE_NAME1 + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SUBJECT + " TEXT, " + COLUMN_POSITION + " INTEGER);";

        String assignmentQuery = "CREATE TABLE " + TABLE_NAME2 + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ASSIGNMENT + " TEXT, " + COLUMN_DATE + " TEXT);";

        db.execSQL(subjectQuery);
        db.execSQL(assignmentQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    void addSubject(String subject, int position) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SUBJECT, subject);
        contentValues.put(COLUMN_POSITION, position);

        long result = db.insert(TABLE_NAME1, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failed to add subject", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Subject added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor loadSubjects() {
        String query = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if (db != null)
            cursor = db.rawQuery(query, null);

        return cursor;

    }


    void addAssignment(String assignment, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ASSIGNMENT,assignment);
        contentValues.put(COLUMN_DATE, date);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failed to add assignment", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Assignment added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor loadAssignments() {
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if (db != null)
            cursor = db.rawQuery(query, null);

        return cursor;

    }
}
