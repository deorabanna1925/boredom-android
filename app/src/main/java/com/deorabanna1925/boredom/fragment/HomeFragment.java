package com.deorabanna1925.boredom.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deorabanna1925.boredom.R;
import com.deorabanna1925.boredom.databinding.ActivityDashboardBinding;
import com.deorabanna1925.boredom.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        return view;
    }
}