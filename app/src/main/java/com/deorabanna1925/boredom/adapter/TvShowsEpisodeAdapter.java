package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.deorabanna1925.boredom.activity.StreamingActivity;
import com.deorabanna1925.boredom.databinding.ItemTvShowEpisodeBinding;
import com.deorabanna1925.boredom.model.ModelTvShow;

import java.util.ArrayList;

public class TvShowsEpisodeAdapter extends RecyclerView.Adapter<TvShowsEpisodeAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<ModelTvShow.Episode> arrayList;
    private final String showId;
    private final String imdbId;

    public TvShowsEpisodeAdapter(Context context, ArrayList<ModelTvShow.Episode> arrayList, String showId, String imdbId) {
        this.context = context;
        this.arrayList = arrayList;
        this.showId = showId;
        this.imdbId = imdbId;
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
            if (model.getSeason() != null && model.getNumber() != null) {
                String idToUse = (imdbId != null && !imdbId.isEmpty()) ? imdbId : showId;
                String path = (imdbId != null && !imdbId.isEmpty()) ? "tv" : "tvmaze";
                
                // For IMDb, the format requested is vsembed.ru/embed/tv/{imdbId}/{season}/{episode}
                // For TvMaze fallback, we use our existing format but with slashes if that works better
                String url;
                if (imdbId != null && !imdbId.isEmpty()) {
                    url = "https://vsembed.ru/embed/tv/" + imdbId + "/" + model.getSeason() + "/" + model.getNumber();
                } else {
                    url = "https://vsembed.ru/embed/tvmaze/" + showId + "/" + model.getSeason() + "-" + model.getNumber();
                }

                Intent intent = new Intent(context, StreamingActivity.class);
                intent.putExtra("url", url);
                context.startActivity(intent);
            }
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
