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
import com.deorabanna1925.boredom.activity.DictionaryActivity;
import com.deorabanna1925.boredom.activity.GradientActivity;
import com.deorabanna1925.boredom.activity.IssLocationActivity;
import com.deorabanna1925.boredom.activity.KanyeWestActivity;
import com.deorabanna1925.boredom.activity.SomethingActivity;
import com.deorabanna1925.boredom.activity.SpeechTextActivity;
import com.deorabanna1925.boredom.activity.TextSpeechActivity;
import com.deorabanna1925.boredom.activity.ThisPersonDoesNotExistActivity;
import com.deorabanna1925.boredom.activity.WhenIsTheNextMcuFilmActivity;
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
        arrayList.add(new ModelExplore("Advice Slip", "Get random advice", AdviceSlipActivity.class));
        arrayList.add(new ModelExplore("Free Dictionary", "Get Free Dictionary", DictionaryActivity.class));
        arrayList.add(new ModelExplore("Random Color", "Generate a random color", ColorsActivity.class));
        arrayList.add(new ModelExplore("Gradient Color", "Get list of gradients", GradientActivity.class));
        arrayList.add(new ModelExplore("Country Activity", "Know about different countries", CountryActivity.class));
        arrayList.add(new ModelExplore("Random Activity", "Bored try something new", SomethingActivity.class));
        arrayList.add(new ModelExplore("Speech to Text", "Convert Speech to Text", SpeechTextActivity.class));
        arrayList.add(new ModelExplore("Text to Speech", "Convert Text to Speech", TextSpeechActivity.class));
        arrayList.add(new ModelExplore("This person does not exist", "This person does not exist in world.", ThisPersonDoesNotExistActivity.class));
        arrayList.add(new ModelExplore("When is the next MCU film ?", "Next MCU Film : Day's Left , Poster, Release Date", WhenIsTheNextMcuFilmActivity.class));
        arrayList.add(new ModelExplore("Kanye West Quote", "Get kanye west random quote", KanyeWestActivity.class));
        arrayList.add(new ModelExplore("ISS Location Activity", "Get current location of international space station", IssLocationActivity.class));

        adapter = new ExploreAdapter(getActivity(),arrayList);
        binding.recyclerView.setAdapter(adapter);

        return view;
    }
}