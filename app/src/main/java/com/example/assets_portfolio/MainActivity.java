package com.example.assets_portfolio;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                            String lotSymbol = intent.getStringExtra("lotSymbol");
                            String lotAmount = intent.getStringExtra("lotAmount");
                            String lotPrice = intent.getStringExtra("lotPrice");


                            addLot(lotSymbol, lotAmount, lotPrice);
                        }
                    }
                }
        );

        btn_addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addingLot.class);
                sender.launch(intent);
            }
        });
        try {
            Log.d("123", "CACHING");
            cacheSymbols();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addLot(String symbol, String amount, String price){
        View newLot = getLayoutInflater().inflate(R.layout.newlot, null);
        rootLayout.addView(newLot);

    }
    public void cacheSymbols() throws IOException {
        JsonObjectRequest jor = new JsonObjectRequest("https://finnhub.io/api/v1/search?q=apple&token=cg8tlupr01qk68o7pk30cg8tlupr01qk68o7pk3g", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                File internalStorageDir = getFilesDir();
                File myFile = new File(internalStorageDir, "symbols.json");

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(myFile);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jor);
    }

}