package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.deorabanna1925.boredom.databinding.ItemTvShowSeasonBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;

import java.util.ArrayList;

public class TvShowsSeasonAdapter extends RecyclerView.Adapter<TvShowsSeasonAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelTvShow.Season> arrayList;

    public TvShowsSeasonAdapter(Context context, ArrayList<ModelTvShow.Season> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TvShowsSeasonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTvShowSeasonBinding binding = ItemTvShowSeasonBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsSeasonAdapter.ViewHolder holder, int position) {
        ModelTvShow.Season model = arrayList.get(position);

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
        }else {
            Glide.with(context)
                    .load("https://static.tvmaze.com/images/no-img/no-img-portrait-text.png")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.image);
        }
        holder.image.setAdjustViewBounds(true);

        holder.itemView.setOnClickListener(v -> {
//            context.startActivity(new Intent(context, TvShowDetailsActivity.class).putExtra("id",model.getId()));
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public ViewHolder(@NonNull ItemTvShowSeasonBinding binding) {
            super(binding.getRoot());
            image = binding.image;
        }

    }
}
