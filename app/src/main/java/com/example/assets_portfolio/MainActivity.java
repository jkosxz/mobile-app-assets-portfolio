package com.example.assets_portfolio;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btn_addView;
    LinearLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addView = findViewById(R.id.addViewButton);
        rootLayout = (LinearLayout) findViewById(R.id.categoryLinearLayout);

        ActivityResultLauncher<Intent> sender = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.d(TAG, "on activity ");
                    if (result.getResultCode() == 2){
                        Intent intent = result.getData();

                        if (intent != null){
                            String catName = intent.getStringExtra("catName");
                            String catDesc = intent.getStringExtra("catDesc");

                            addCategory(catName, catDesc);
                        }
                    }
                }
        );

        btn_addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addNewCategoryActivity.class);
                sender.launch(intent);
            }
        });
    }
    public void addCategory(String catName, String catDesc){
        View child = getLayoutInflater().inflate(R.layout.test, null);
        rootLayout.addView(child);
        TextView name = findViewById(R.id.categoryCard);
        name.setText(catName);

    }
    public void goToCategory(View v){
        Intent i = new Intent(this, categoryActivity.class);
        startActivity(i);
    }

}