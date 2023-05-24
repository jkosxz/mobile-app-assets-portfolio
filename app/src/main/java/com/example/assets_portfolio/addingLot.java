package com.example.assets_portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class addingLot extends AppCompatActivity {
    TextView lotSymbol, lotAmount, lotPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_lot);

        lotSymbol = (TextView) findViewById(R.id.lotSymbol_et);
        lotAmount = (TextView) findViewById(R.id.lotAmount_et);
        lotPrice = (TextView) findViewById(R.id.lotPrice_et);
    }

    public void addLotToMain(View v){
        Intent i = new Intent();
        i.putExtra("lotSymbol", lotSymbol.getText().toString());
        i.putExtra("lotAmount", lotAmount.getText().toString());
        i.putExtra("lotPrice", lotPrice.getText().toString());

        setResult(2, i);
        finish();

    }
}