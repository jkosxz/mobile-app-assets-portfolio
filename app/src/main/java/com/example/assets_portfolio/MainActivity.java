package com.example.assets_portfolio;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONException;
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
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btn_addView;
    LinearLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addView = findViewById(R.id.addViewButton);
        rootLayout = findViewById(R.id.categoryLinearLayout);

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

        btn_addView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, addingLot.class);
            sender.launch(intent);
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
        newLot.setTag(symbol);
        TextView lotSymbol = newLot.findViewById(R.id.chSymbol);
        lotSymbol.setText(symbol);

        TextView lotAmount = newLot.findViewById(R.id.chAmount);
        lotAmount.setText(amount);

        TextView priceBought = newLot.findViewById(R.id.chPricebought);
        priceBought.setText(price);


        JsonObjectRequest jor = new JsonObjectRequest(String.format("https://finnhub.io/api/v1/quote?symbol=%s&token=cg8tlupr01qk68o7pk30cg8tlupr01qk68o7pk3g", symbol),
                null, response -> {
                    try {
                        String apiShotResult = response.get("c").toString();
                        Log.d("response", apiShotResult);

                        TextView currentPrice = newLot.findViewById(R.id.chCurrentprice);
                        currentPrice.setText(String.format("%s$", apiShotResult));

                        if (!apiShotResult.equals("")){
                            double bilansAmount = (Integer.parseInt(amount) * Double.parseDouble(apiShotResult) - Double.parseDouble(amount)*Integer.parseInt(price));
                            TextView bilans = newLot.findViewById(R.id.chBilans);
                            bilans.setText(String.format(Locale.ENGLISH, "%.2f", bilansAmount));
                            if (bilansAmount >= 0){
                                bilans.setTextColor(Color.parseColor("#7CFC00"));
                            }
                            else{
                                bilans.setTextColor(Color.parseColor("#FF0000"));
                            }

                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }, error -> {
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jor);

    }
    public void cacheSymbols() throws IOException {
        JsonObjectRequest jor = new JsonObjectRequest("https://finnhub.io/api/v1/search?q=apple&token=cg8tlupr01qk68o7pk30cg8tlupr01qk68o7pk3g",
                null, response -> {
                    File internalStorageDir = getFilesDir();
                    File myFile = new File(internalStorageDir, "symbols.json");

                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myFile);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }, error -> {
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jor);
    }

    public void deleteLot(View v){
        rootLayout.removeView((View) v.getParent());
    }

    public void goToCalendar(View v){
        Intent i = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(i);
    }

}