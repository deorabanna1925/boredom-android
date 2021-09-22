package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemMainExploreBinding;
import com.deorabanna1925.boredom.model.ModelExplore;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelExplore> arrayList;

    public HomeAdapter(Context context, ArrayList<ModelExplore> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMainExploreBinding binding = ItemMainExploreBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        ModelExplore model = arrayList.get(position);

        holder.title.setText(model.getTitle());
        holder.subTitle.setText(model.getSub_title());
        holder.open.setOnClickListener(view -> {
            context.startActivity(new Intent(context, model.getActivity()));
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView subTitle;
        public ImageView open;

        public ViewHolder(@NonNull ItemMainExploreBinding binding) {
            super(binding.getRoot());

            title = binding.title;
            subTitle = binding.subTitle;
            open = binding.open;

        }

    }
}
