package com.deorabanna1925.boredom.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.adapter.GradientAdapter;
import com.deorabanna1925.boredom.databinding.ActivityGradientBinding;
import com.deorabanna1925.boredom.model.ModelGradient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class GradientActivity extends AppCompatActivity {

    private ArrayList<ModelGradient> arrayList;
    private GradientAdapter adapter;

    private ActivityGradientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGradientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);

        getGradientData();

    }

    private void getGradientData() {

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
                    binding.recyclerView.setAdapter(adapter);
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