package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemCollegeBinding;
import com.deorabanna1925.boredom.model.ModelCollege;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<ModelCollege> arrayList;

    public CollegeAdapter(Context context, ArrayList<ModelCollege> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CollegeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCollegeBinding binding = ItemCollegeBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CollegeAdapter.ViewHolder holder, int position) {
        ModelCollege model = arrayList.get(position);

        holder.count.setText(String.valueOf(position + 1));
        holder.name.setText(model.getName());
        holder.domain.setText(model.getDomains().get(0));

        holder.visit.setOnClickListener(view -> {
            new FinestWebView.Builder(context).show(model.getWeb_pages().get(0));
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView domain;
        public TextView count;
        public ImageView visit;

        public ViewHolder(@NonNull ItemCollegeBinding binding) {
            super(binding.getRoot());

            name = binding.name;
            domain = binding.domain;
            count = binding.count;
            visit = binding.visit;


        }

    }
}
