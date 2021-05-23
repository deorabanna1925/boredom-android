package com.deorabanna1925.boredom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.R;
import com.deorabanna1925.boredom.adapter.GradientAdapter;
import com.deorabanna1925.boredom.model.ModelGradient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class GradientActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ModelGradient> arrayList;
    private GradientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient);

        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getGradientData();

    }

    private void getGradientData() {
        /*
            Data = 382
            2 = 337
            3 = 36
            4 = 4
            5 = 4
            6 = 1
         */

        arrayList = new ArrayList<>();
        String url = "https://raw.githubusercontent.com/ghosh/uiGradients/master/gradients.json";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    JSONArray colors = jsonObject.getJSONArray("colors");
                    ArrayList<String> allColors = new ArrayList<>();
                    for(int j = 0;j<colors.length();j++){
                        String value = colors.getString(j);
                        allColors.add(value);
                    }
                    arrayList.add(new ModelGradient(name,allColors));
                    Collections.shuffle(arrayList);
                    adapter = new GradientAdapter(GradientActivity.this,arrayList);
                    recyclerView.setAdapter(adapter);
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