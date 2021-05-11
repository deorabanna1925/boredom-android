package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.R;
import com.deorabanna1925.boredom.model.ModelSomething;

import java.util.ArrayList;

public class SomethingAdapter extends RecyclerView.Adapter<SomethingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelSomething> arrayList;

    public SomethingAdapter(Context context, ArrayList<ModelSomething> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SomethingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_something, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SomethingAdapter.ViewHolder holder, int position) {
        ModelSomething model = arrayList.get(position);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
