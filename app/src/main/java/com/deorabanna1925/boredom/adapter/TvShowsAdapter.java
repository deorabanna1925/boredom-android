package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.R;
import com.deorabanna1925.boredom.activity.TvShowDetailsActivity;
import com.deorabanna1925.boredom.databinding.ItemTvShowSearchBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;

import java.util.ArrayList;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelTvShow> arrayList;

    public TvShowsAdapter(Context context, ArrayList<ModelTvShow> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TvShowsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTvShowSearchBinding binding = ItemTvShowSearchBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsAdapter.ViewHolder holder, int position) {
        ModelTvShow modelTvShow = arrayList.get(position);
        ModelTvShow.Show model = modelTvShow.getShow();

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        if(model.getImage()!=null){
            Glide.with(context)
                    .load(model.getImage().getMedium())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(progressDrawable)
                    .skipMemoryCache(true)
                    .into(holder.image);
            holder.image.setAdjustViewBounds(true);
        }else {
            Glide.with(context)
                    .load(R.drawable.ic_logo)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.image);
            holder.image.setAdjustViewBounds(true);
        }

        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(context, TvShowDetailsActivity.class).putExtra("id",model.getId()));
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public ViewHolder(@NonNull ItemTvShowSearchBinding binding) {
            super(binding.getRoot());
            image = binding.image;
        }

    }
}
