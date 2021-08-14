package com.deorabanna1925.boredom.activity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.deorabanna1925.boredom.adapter.DictionaryAdapter;
import com.deorabanna1925.boredom.databinding.ActivityDictionaryBinding;
import com.deorabanna1925.boredom.model.ModelDictionary;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {

    private ActivityDictionaryBinding binding;
    private DictionaryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.progressBar.setVisibility(View.GONE);

        binding.searchBar.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                getDictionaryData(textView.getText().toString());
                return true;
            }
            return false;
        });

    }

    private void getDictionaryData(String search) {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + search;
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                    JSONArray jsonArray = new JSONArray(response);
                    binding.progressBar.setVisibility(View.GONE);
                    Gson gson = new Gson();
                    String jsonOutput = jsonArray.toString();
                    Type listType = new TypeToken<ArrayList<ModelDictionary>>() {
                    }.getType();
                    ArrayList<ModelDictionary> arrayList = gson.fromJson(jsonOutput, listType);
                    adapter = new DictionaryAdapter(DictionaryActivity.this, arrayList);
                    binding.recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            binding.progressBar.setVisibility(View.GONE);
            //get status code here
            String statusCode = String.valueOf(error.networkResponse.statusCode);
            //get response body and parse with appropriate encoding
            if(error.networkResponse.data!=null) {
                try {
                    String response = new String(error.networkResponse.data, StandardCharsets.UTF_8);

                    JSONObject jsonObject = new JSONObject(response);

                    String title = jsonObject.getString("title");
                    String message = jsonObject.getString("message");
                    String resolution = jsonObject.getString("resolution");

                    Toast.makeText(this, "" + title, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "" + resolution, Toast.LENGTH_SHORT).show();

                    ArrayList<ModelDictionary> arrayList = new ArrayList<>();
                    adapter = new DictionaryAdapter(DictionaryActivity.this, arrayList);
                    binding.recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        queue.add(request);
    }

}