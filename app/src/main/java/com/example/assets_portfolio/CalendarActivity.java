package com.example.assets_portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    Button btnsavenote;
    TextView noteText;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        title = findViewById(R.id.title_et);
        btnsavenote = findViewById(R.id.btnAddNote);
        noteText = findViewById(R.id.note_et);

        btnsavenote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!title.getText().toString().isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent
                }
            }
        });
    }


}