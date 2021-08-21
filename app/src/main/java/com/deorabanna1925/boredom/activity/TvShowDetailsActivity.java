package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.adapter.TvShowsSeasonAdapter;
import com.deorabanna1925.boredom.databinding.ActivityTvShowDetailsBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class TvShowDetailsActivity extends AppCompatActivity {

    private ActivityTvShowDetailsBinding binding;
    private TvShowsSeasonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTvShowDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        int id = getIntent().getIntExtra("id", 1);

        getTvShowData(String.valueOf(id));
        getTvShowSeasonData(String.valueOf(id));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getTvShowData(String id) {

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        String url = "https://api.tvmaze.com/shows/" + id;
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                Gson gson = new Gson();
                String jsonOutput = jsonObject.toString();
                Type listType = new TypeToken<ModelTvShow.Show>() {
                }.getType();
                ModelTvShow.Show model = gson.fromJson(jsonOutput, listType);

                if (model.getImage() != null) {

                    Glide.with(this)
                            .load(model.getImage().getOriginal())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(progressDrawable)
                            .skipMemoryCache(true)
                            .into(binding.image);

                    Glide.with(this)
                            .load(model.getImage().getMedium())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(bitmapTransform(new BlurTransformation(25, 3)))
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.imageBackground);

                } else {
                    Glide.with(this)
                            .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(progressDrawable)
                            .skipMemoryCache(true)
                            .into(binding.image);

                    Glide.with(this)
                            .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(bitmapTransform(new BlurTransformation(25, 3)))
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.imageBackground);
                }

                if (model.getName() != null)
                    binding.name.setText(model.getName());
                if (model.getStatus() != null)
                    binding.status.setText(model.getStatus());
                if (model.getSummary() != null)
                    binding.summary.setText(Html.fromHtml(model.getSummary()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

    private void getTvShowSeasonData(String id) {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        String url = "https://api.tvmaze.com/shows/" + id + "/seasons";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelTvShow.Season>>() {
                }.getType();
                ArrayList<ModelTvShow.Season> arrayList = gson.fromJson(jsonOutput, listType);
                adapter = new TvShowsSeasonAdapter(TvShowDetailsActivity.this, arrayList);
                binding.recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

}