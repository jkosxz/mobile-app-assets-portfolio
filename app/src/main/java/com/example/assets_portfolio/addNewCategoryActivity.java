package com.example.assets_portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class addNewCategoryActivity extends AppCompatActivity {
    TextView categoryName, categoryDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);
        categoryName = (TextView) findViewById(R.id.catName_et);
        categoryDescription = (TextView) findViewById(R.id.description_et);
    }

    public void addCategoryToMain(View v){
        Intent i = new Intent();
        i.putExtra("catName", categoryName.getText().toString());
        i.putExtra("catDesc", categoryDescription.getText().toString());
        setResult(2, i);
        finish();

    }
}