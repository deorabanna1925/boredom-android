package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ActivityFoodsBinding;

import org.json.JSONException;
import org.json.JSONObject;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class FoodsActivity extends AppCompatActivity {

    private ActivityFoodsBinding binding;
    private CircularProgressDrawable progressDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        binding.generateNew.setOnClickListener(view -> getData());

        getData();

    }

    private void getData() {
        String url = "https://foodish-api.herokuapp.com/api/";
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String image = jsonObject.getString("image");

                Glide.with(this)
                        .load(image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(progressDrawable)
                        .skipMemoryCache(true)
                        .into(binding.image);

                Glide.with(this)
                        .load(image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(bitmapTransform(new BlurTransformation(25, 3)))
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(binding.backgroundImage);

                binding.backgroundImage.setAlpha(0.3f);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            binding.progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

}