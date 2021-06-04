package com.deorabanna1925.boredom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.deorabanna1925.boredom.R;
import com.deorabanna1925.boredom.databinding.FragmentExploreBinding;
import com.deorabanna1925.boredom.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}