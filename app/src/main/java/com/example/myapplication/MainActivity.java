package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Intent intent;


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

        intent= new Intent(this,AddSubjectActivity.class);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
                finish();
            }
        });


/*

        gridLayout=findViewById(R.id.gridLayout);
        TextView textView= (TextView) gridLayout.getChildAt(1);


        String msg= "Introduction to Algorithms and Data Structures \n\n";
        String time= "12:59\nPM\n | \n12:59\nPM";

        int msgLength=msg.length();
        int timeLength=time.length();

        String total=msg+time;

        SpannableString ss = new SpannableString(total);

        ForegroundColorSpan fcsWhite =new ForegroundColorSpan(Color.WHITE);
        ss.setSpan(fcsWhite,msgLength+0,msgLength+timeLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setBackgroundColor(Color.CYAN);

        System.out.println(msgLength);
        System.out.println(timeLength);



        TextView textView1= (TextView) gridLayout.getChildAt(6);
        textView1.setText("Algoritasdasdasdasdasdhms");
        textView1.setBackgroundColor(Color.GREEN);


        TextView textView2= (TextView) gridLayout.getChildAt(2);
        textView2.setText(total);
        textView2.setBackgroundColor(Color.GREEN);
*/




    }
}