package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ActivityTvShowDetailsBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class TvShowDetailsActivity extends AppCompatActivity {

    private ActivityTvShowDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTvShowDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        int id = getIntent().getIntExtra("id",1);

        getTvShowData(String.valueOf(id));

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

                binding.imageBackground.setAlpha(0.3f);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

}