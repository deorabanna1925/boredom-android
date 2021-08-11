package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ActivityThisPersonDoesNotExistBinding;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ThisPersonDoesNotExistActivity extends AppCompatActivity {

    private ActivityThisPersonDoesNotExistBinding binding;
    private CircularProgressDrawable progressDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThisPersonDoesNotExistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        progressDrawable = new CircularProgressDrawable(this);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        String url = "https://thispersondoesnotexist.com/image";

        binding.generateNew.setOnClickListener(view -> getImage(url));

        getImage(url);
    }

    private void getImage(String url) {

        Glide.with(this)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(progressDrawable)
                .skipMemoryCache(true)
                .into(binding.image);

        Glide.with(this)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.backgroundImage);

        binding.backgroundImage.setAlpha(0.3f);

    }
}