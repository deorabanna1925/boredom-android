package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemDictionaryMeaningsBinding;
import com.deorabanna1925.boredom.model.ModelDictionary;

import java.util.ArrayList;

public class DictionaryMeaningsAdapter extends RecyclerView.Adapter<DictionaryMeaningsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelDictionary.Meanings> arrayList;

    public DictionaryMeaningsAdapter(Context context, ArrayList<ModelDictionary.Meanings> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DictionaryMeaningsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDictionaryMeaningsBinding binding = ItemDictionaryMeaningsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryMeaningsAdapter.ViewHolder holder, int position) {
        ModelDictionary.Meanings model = arrayList.get(position);

        holder.partOfSpeech.setText(model.getPartOfSpeech());

        holder.definitions.setLayoutManager(new LinearLayoutManager(context));
        holder.definitions.setNestedScrollingEnabled(false);
        holder.definitions.setHasFixedSize(true);
        holder.definitions.setAdapter(new DictionaryMeaningsDefinitionsAdapter(context,model.getDefinitions()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView partOfSpeech;
        public RecyclerView definitions;

        public ViewHolder(@NonNull ItemDictionaryMeaningsBinding binding) {
            super(binding.getRoot());
            partOfSpeech = binding.partOfSpeech;
            definitions = binding.definitions;
        }

    }
}
