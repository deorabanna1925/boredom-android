package com.deorabanna1925.boredom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deorabanna1925.boredom.databinding.ItemDictionaryMeaningsDefinitionsBinding;
import com.deorabanna1925.boredom.model.ModelDictionary;

import java.util.ArrayList;

public class DictionaryMeaningsDefinitionsAdapter extends RecyclerView.Adapter<DictionaryMeaningsDefinitionsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelDictionary.Meanings.Definitions> arrayList;

    public DictionaryMeaningsDefinitionsAdapter(Context context, ArrayList<ModelDictionary.Meanings.Definitions> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DictionaryMeaningsDefinitionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDictionaryMeaningsDefinitionsBinding binding = ItemDictionaryMeaningsDefinitionsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryMeaningsDefinitionsAdapter.ViewHolder holder, int position) {
        ModelDictionary.Meanings.Definitions model = arrayList.get(position);

        holder.definition.setText(model.getDefinition());
        holder.example.setText(model.getExample());
        String syn = "";
        for(String synonyms : model.getSynonyms()){
            syn = synonyms + ", ";
            holder.synonyms.setText(syn);
        }
        String ant = "";
        for(String antonyms : model.getAntonyms()){
            ant = antonyms + ", ";
            holder.antonyms.setText(ant);
        }



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView definition;
        public TextView example;
        public TextView synonyms;
        public TextView antonyms;

        public ViewHolder(@NonNull ItemDictionaryMeaningsDefinitionsBinding binding) {
            super(binding.getRoot());
            definition = binding.definition;
            example = binding.example;
            synonyms = binding.synonyms;
            antonyms = binding.antonyms;
        }

    }
}
