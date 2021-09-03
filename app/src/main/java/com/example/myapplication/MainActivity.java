package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    public static int fragmentNumber=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fragmentNumber==1) {
                    intent= new Intent(MainActivity.this,AddSubjectActivity.class);
                    startActivity(intent);
                    finish();
                }

                if(fragmentNumber==2) {

                    intent= new Intent(MainActivity.this,AddAssignmentActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}