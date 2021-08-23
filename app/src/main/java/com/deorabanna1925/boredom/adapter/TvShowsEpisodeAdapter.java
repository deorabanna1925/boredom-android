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
import com.deorabanna1925.boredom.databinding.ItemTvShowEpisodeBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;

import java.util.ArrayList;

public class TvShowsEpisodeAdapter extends RecyclerView.Adapter<TvShowsEpisodeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelTvShow.Episode> arrayList;

    public TvShowsEpisodeAdapter(Context context, ArrayList<ModelTvShow.Episode> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TvShowsEpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTvShowEpisodeBinding binding = ItemTvShowEpisodeBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsEpisodeAdapter.ViewHolder holder, int position) {
        ModelTvShow.Episode model = arrayList.get(position);

        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();

        if(model.getImage()!=null){
            Glide.with(context)
                    .load(model.getImage().getOriginal())
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

        if(model.getName()!=null){
            holder.name.setText(model.getName());
        }
        if(model.getSeason()!=null){
            holder.season.setText("S"+model.getSeason());
        }
        if(model.getNumber()!=null){
            holder.episode.setText("E"+model.getNumber());
        }
        if(model.getAirdate()!=null){
            holder.airDate.setText(model.getAirdate());
        }

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
        public TextView name;
        public TextView season;
        public TextView episode;
        public TextView airDate;

        public ViewHolder(@NonNull ItemTvShowEpisodeBinding binding) {
            super(binding.getRoot());
            image = binding.image;
            name = binding.name;
            season = binding.season;
            episode = binding.episode;
            airDate = binding.airDate;
        }

    }
}
