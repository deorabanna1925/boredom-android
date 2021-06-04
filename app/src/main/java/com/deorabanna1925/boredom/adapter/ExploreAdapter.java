package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.R;
import com.deorabanna1925.boredom.model.ModelExplore;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelExplore> arrayList;

    public ExploreAdapter(Context context, ArrayList<ModelExplore> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExploreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_explore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ViewHolder holder, int position) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.sub_title);
            open = itemView.findViewById(R.id.open);

        }

    }
}
