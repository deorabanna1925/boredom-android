package com.deorabanna1925.boredom.activity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.deorabanna1925.boredom.databinding.ActivityThisPersonDoesNotExistBinding;
import com.stfalcon.imageviewer.StfalconImageViewer;

import java.util.ArrayList;
import java.util.Collections;

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
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(progressDrawable)
                .skipMemoryCache(true)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        Glide.with(ThisPersonDoesNotExistActivity.this)
                                .load(resource)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(binding.image);

                        Glide.with(ThisPersonDoesNotExistActivity.this)
                                .load(resource)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                                .into(binding.backgroundImage);

                        binding.backgroundImage.setAlpha(0.3f);

                        binding.image.setOnClickListener(view ->
                                new StfalconImageViewer.Builder<>(ThisPersonDoesNotExistActivity.this,
                                new ArrayList<>(Collections.singletonList(resource)), (imageView, bitmap) ->
                                Glide.with(ThisPersonDoesNotExistActivity.this)
                                        .load(bitmap)
                                        .transition(DrawableTransitionOptions.withCrossFade())
                                        .into(imageView)).withHiddenStatusBar(false)
                                .withTransitionFrom(binding.image)
                                .show());

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                });

    }
}