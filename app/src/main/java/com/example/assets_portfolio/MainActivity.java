package com.example.assets_portfolio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = findViewById(R.id.addViewButton);


    }
    public void addCategory(View view){
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.categoryLinearLayout);
        View child = getLayoutInflater().inflate(R.layout.test, null);
        rootLayout.addView(child);

    }

    public void goToAddNewCategory(View v){
        Intent i = new Intent(this, addNewCategoryActivity.class);
        startActivityForResult(i, 0); // ma zwracac pakiet stringow potrzebnych do zrobienia view category i potem dodania go
    }



}