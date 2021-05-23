package com.deorabanna1925.boredom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.activity.ColorsActivity;
import com.deorabanna1925.boredom.activity.GradientActivity;
import com.deorabanna1925.boredom.activity.SomethingActivity;
import com.deorabanna1925.boredom.adapter.GradientAdapter;
import com.deorabanna1925.boredom.model.ModelGradient;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void openColors(View view) {
        startActivity(new Intent(MainActivity.this, ColorsActivity.class));
    }

    public void openGradient(View view) {
        startActivity(new Intent(MainActivity.this, GradientActivity.class));
    }

    public void openSomething(View view) {
        startActivity(new Intent(MainActivity.this, SomethingActivity.class));
    }

    public void getIssCoordiantes(View view) {
        String url = "http://api.open-notify.org/iss-now.json";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String message = jsonObject.getString("message");
                if (message.equals("success")) {
                    JSONObject iss_position = jsonObject.getJSONObject("iss_position");
                    String longitude = iss_position.getString("longitude");
                    String latitude = iss_position.getString("latitude");

                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(latitude), 1);
                        if (addresses != null && !addresses.isEmpty()) {

                            String cityName = addresses.get(0).getAddressLine(0);
                            String stateName = addresses.get(0).getAddressLine(1);
                            String countryName = addresses.get(0).getAddressLine(2);

                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout, "International Space Station is above\n"+ cityName, Snackbar.LENGTH_LONG)
                                    .setAction("Open Map", v -> {
                                        String uri = String.format(Locale.ENGLISH,
                                                "http://maps.google.com/maps?q=loc:%f,%f",
                                                Double.parseDouble(latitude),
                                                Double.parseDouble(longitude)
                                        );
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                        startActivity(intent);
                                    })
                                    .setActionTextColor(Color.parseColor("#0099ff"))
                                    .show();

                        } else {
                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout, "International Space Station is above\nLocation Unknown", Snackbar.LENGTH_LONG)
                                    .setAction("Open Map", v -> {
                                        String uri = String.format(Locale.ENGLISH,
                                                "http://maps.google.com/maps?q=loc:%f,%f",
                                                Double.parseDouble(latitude),
                                                Double.parseDouble(longitude)
                                        );
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                        startActivity(intent);
                                    })
                                    .setActionTextColor(Color.parseColor("#0099ff"))
                                    .show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

}