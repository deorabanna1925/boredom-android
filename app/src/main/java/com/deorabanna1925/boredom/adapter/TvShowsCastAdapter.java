package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ItemTvShowCastBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;
import com.stfalcon.imageviewer.StfalconImageViewer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class TvShowsCastAdapter extends RecyclerView.Adapter<TvShowsCastAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelTvShow.Cast> arrayList;
    private ArrayList<String> imagesPerson, imagesCharacter;

    public TvShowsCastAdapter(Context context, ArrayList<ModelTvShow.Cast> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.imagesPerson = new ArrayList<>();
        this.imagesCharacter = new ArrayList<>();
    }

    @NonNull
    @Override
    public TvShowsCastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTvShowCastBinding binding = ItemTvShowCastBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsCastAdapter.ViewHolder holder, int position) {
        ModelTvShow.Cast model = arrayList.get(position);

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        if (model.getPerson().getImage() != null) {
            imagesPerson.add(model.getPerson().getImage().getOriginal());
            Glide.with(context)
                    .load(model.getPerson().getImage().getMedium())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(progressDrawable)
                    .skipMemoryCache(true)
                    .into(holder.personImage);
        } else {
            imagesPerson.add("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png");
            Glide.with(context)
                    .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.personImage);
        }
        holder.personImage.setAdjustViewBounds(true);

        if (model.getCharacter().getImage() != null) {
            imagesCharacter.add(model.getCharacter().getImage().getOriginal());
            Glide.with(context)
                    .load(model.getCharacter().getImage().getMedium())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(progressDrawable)
                    .skipMemoryCache(true)
                    .into(holder.characterImage);
        } else {
            imagesCharacter.add("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png");
            Glide.with(context)
                    .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.characterImage);
        }
        holder.characterImage.setAdjustViewBounds(true);

        holder.personImage.setOnClickListener(v -> {
            imageFullScreen(model.getPerson().getImage().getOriginal());
        });

        holder.characterImage.setOnClickListener(v -> {
            imageFullScreen(model.getCharacter().getImage().getOriginal());
        });

        if (model.getPerson().getName() != null) {
            holder.personName.setText(model.getPerson().getName());
        }

        if (model.getCharacter().getName() != null) {
            holder.characterName.setText(model.getCharacter().getName());
        }

    }

    private void imageFullScreen(String original) {
        ArrayList<String> image = new ArrayList<>();
        image.add(original);
        new StfalconImageViewer.Builder<>(context, image, (imageView, image1) -> {
            CircularProgressDrawable progressDrawable1 = new CircularProgressDrawable(context);
            progressDrawable1.setStrokeWidth(5f);
            progressDrawable1.setCenterRadius(30f);
            progressDrawable1.start();
            Glide.with(context)
                    .load(image1)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(progressDrawable1)
                    .skipMemoryCache(true)
                    .into(imageView);
        }).withHiddenStatusBar(false).show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView personImage;
        public TextView personName;
        public TextView characterName;
        public ImageView characterImage;

        public ViewHolder(@NonNull ItemTvShowCastBinding binding) {
            super(binding.getRoot());
            personImage = binding.personImage;
            personName = binding.personName;
            characterName = binding.characterName;
            characterImage = binding.characterImage;
        }

    }
}
