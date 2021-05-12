package com.deorabanna1925.boredom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ColorsActivity extends AppCompatActivity {

    //Hex Value
    private TextView hexValue;

    //RGB Value
    private TextView rValue;
    private TextView gValue;
    private TextView bValue;

    //HSV Value
    private TextView hValue;
    private TextView sValue;
    private TextView vValue;

    //Color Image
    private ImageView colorImage;
    private ImageView colorImageBackground;

    //Progress Bar
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        getSupportActionBar().hide();

        hexValue = findViewById(R.id.hex_value);

        rValue = findViewById(R.id.r_value);
        gValue = findViewById(R.id.g_value);
        bValue = findViewById(R.id.b_value);

        hValue = findViewById(R.id.h_value);
        sValue = findViewById(R.id.s_value);
        vValue = findViewById(R.id.v_value);

        colorImage = findViewById(R.id.color_image);
        colorImageBackground = findViewById(R.id.color_image_background);

        progressBar = findViewById(R.id.progress_bar);

        getColorData();

    }

    private void getColorData() {
        progressBar.setVisibility(View.VISIBLE);
        String url = "http://www.colourlovers.com/api/colors/random&format=json";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            progressBar.setVisibility(View.GONE);
            try {
                JSONArray jsonArray = new JSONArray(response);
//                arrayList.clear();
                if (jsonArray != null) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String hex = jsonObject.getString("hex");

                    JSONObject rgb = jsonObject.getJSONObject("rgb");
                    JSONObject hsv = jsonObject.getJSONObject("hsv");

                    int r = rgb.getInt("red");
                    int g = rgb.getInt("green");
                    int b = rgb.getInt("blue");

                    int h = hsv.getInt("hue");
                    int s = hsv.getInt("saturation");
                    int v = hsv.getInt("value");

                    String hexCode = "#" + hex;

                    hexValue.setText(hexCode);

                    rValue.setText(String.valueOf(r));
                    gValue.setText(String.valueOf(g));
                    bValue.setText(String.valueOf(b));

                    hValue.setText(String.valueOf(h));
                    sValue.setText(String.valueOf(s));
                    vValue.setText(String.valueOf(v));

                    colorImage.setColorFilter(Color.parseColor(hexCode));
                    colorImageBackground.setColorFilter(Color.parseColor(hexCode));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

    public void generateNew(View view) {
        getColorData();
    }
}