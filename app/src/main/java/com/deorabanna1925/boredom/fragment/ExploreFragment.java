package com.deorabanna1925.boredom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.deorabanna1925.boredom.activity.AdviceSlipActivity;
import com.deorabanna1925.boredom.activity.ColorsActivity;
import com.deorabanna1925.boredom.activity.CountryActivity;
import com.deorabanna1925.boredom.activity.GradientActivity;
import com.deorabanna1925.boredom.activity.KanyeWestActivity;
import com.deorabanna1925.boredom.activity.SomethingActivity;
import com.deorabanna1925.boredom.adapter.ExploreAdapter;
import com.deorabanna1925.boredom.databinding.FragmentExploreBinding;
import com.deorabanna1925.boredom.model.ModelExplore;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {

    public ExploreFragment() {
        // Required empty public constructor
    }
    private FragmentExploreBinding binding;
    private ArrayList<ModelExplore> arrayList;
    private ExploreAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();
        arrayList.add(new ModelExplore("Advice Slip", "Get Random Advice", AdviceSlipActivity.class));
        arrayList.add(new ModelExplore("Random Color", "Generate a Random Color", ColorsActivity.class));
        arrayList.add(new ModelExplore("Gradient Color", "Get List of Gradients", GradientActivity.class));
        arrayList.add(new ModelExplore("Country Activity", "Know about different countries", CountryActivity.class));
        arrayList.add(new ModelExplore("Random Activity", "Bored try something new", SomethingActivity.class));
        arrayList.add(new ModelExplore("Kanye West Quote ", "Get Kanye West Random Quote", KanyeWestActivity.class));

        adapter = new ExploreAdapter(getActivity(),arrayList);
        binding.recyclerView.setAdapter(adapter);

        return view;
    }
}