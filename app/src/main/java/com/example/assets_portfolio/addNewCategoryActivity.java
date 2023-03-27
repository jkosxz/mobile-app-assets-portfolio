package com.example.assets_portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;

public class addNewCategoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);
    }

    public void addCategoryToMain(View v){
        LinearLayout layout = (LinearLayout) findViewById(R.id.categoryLinearLayout);
        View child = getLayoutInflater().inflate(R.layout.test, null);
        layout.addView(child);
        Intent i = new Intent();
        setResult(2, i);
        finish();

    }
}