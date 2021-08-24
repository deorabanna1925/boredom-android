package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ItemTvShowCastBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;

import java.util.ArrayList;

public class TvShowsCastAdapter extends RecyclerView.Adapter<TvShowsCastAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelTvShow.Cast> arrayList;

    public TvShowsCastAdapter(Context context, ArrayList<ModelTvShow.Cast> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
            Glide.with(context)
                    .load(model.getPerson().getImage().getMedium())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(progressDrawable)
                    .skipMemoryCache(true)
                    .into(holder.personImage);
        } else {
            Glide.with(context)
                    .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.personImage);
        }
        holder.personImage.setAdjustViewBounds(true);

        if (model.getCharacter().getImage() != null) {
            Glide.with(context)
                    .load(model.getCharacter().getImage().getMedium())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(progressDrawable)
                    .skipMemoryCache(true)
                    .into(holder.characterImage);
        } else {
            Glide.with(context)
                    .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.characterImage);
        }
        holder.characterImage.setAdjustViewBounds(true);

        if(model.getPerson().getName()!=null){
            holder.personName.setText(model.getPerson().getName());
        }

        if(model.getCharacter().getName()!=null){
            holder.characterName.setText(model.getCharacter().getName());
        }

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
