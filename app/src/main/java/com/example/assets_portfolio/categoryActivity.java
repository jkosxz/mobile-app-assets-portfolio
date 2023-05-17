package com.example.assets_portfolio;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class categoryActivity extends AppCompatActivity {

    LinearLayout lotLayout;
    Button btn_addLot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        lotLayout = (LinearLayout) findViewById(R.id.lotLayout);
        btn_addLot = (Button) findViewById(R.id.btn_addLot);

        ActivityResultLauncher<Intent> sender = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.d(TAG, "on activity ");
                    if (result.getResultCode() == 2){
                        Intent intent = result.getData();

                        if (intent != null){
                            String catName = intent.getStringExtra("catName");
                            String catDesc = intent.getStringExtra("catDesc");

                            addLot(catName, catDesc);
                        }
                    }
                }
        );

        btn_addLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categoryActivity.this, addNewLot.class);
                sender.launch(intent);
            }
        });
    }
    public void addLot(String catName, String catDesc){
        View child = getLayoutInflater().inflate(R.layout.test, null);
        lotLayout.addView(child);
        TextView name = findViewById(R.id.categoryCard);
        name.setText(catName);

    }




}