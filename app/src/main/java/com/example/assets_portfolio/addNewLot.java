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

import org.w3c.dom.Text;

public class addNewLot extends AppCompatActivity {

    LinearLayout lotLayout;
    TextView lotName, lotAmount, lotPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lot);

        lotName = (TextView) findViewById(R.id.lotSymbol_et);
        lotAmount = (TextView) findViewById(R.id.lotAmount_et);
        lotPrice = (TextView) findViewById(R.id.lotPrice_et);
    }
    public void addCategoryToMain(View v){
        Intent i = new Intent();
        i.putExtra("lotName", lotName.getText().toString());
        i.putExtra("lotAmount", lotAmount.getText().toString());
        i.putExtra("lotPrice", lotPrice.getText().toString());
        setResult(2, i);
        finish();

    }

}