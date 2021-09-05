package com.deorabanna1925.boredom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.deorabanna1925.boredom.databinding.FragmentSettingsBinding;
import com.deorabanna1925.boredom.shared.Prefs;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    private FragmentSettingsBinding binding;
    private Prefs prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        prefs = new Prefs(requireActivity());

        //Info
        binding.aboutUs.setOnClickListener(v -> Toast.makeText(requireContext(), "" + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show());
        binding.contactUs.setOnClickListener(v -> Toast.makeText(requireContext(), "" + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show());
        binding.visitUs.setOnClickListener(v -> Toast.makeText(requireContext(), "" + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show());

        //Tools
        binding.changeTheme.setOnClickListener(v -> {
            openThemeDialog();
        });
        binding.checkForUpdate.setOnClickListener(v -> Toast.makeText(requireContext(), "" + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show());
        binding.inviteYourFriends.setOnClickListener(v -> Toast.makeText(requireContext(), "" + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show());

        //Support
        binding.faq.setOnClickListener(v -> Toast.makeText(requireContext(), "" + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show());
        binding.privacyPolicy.setOnClickListener(v -> Toast.makeText(requireContext(), "" + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show());

        return binding.getRoot();
    }

    private void openThemeDialog() {
        String[] items = {"System default", "Light", "Dark"};
        int checkedItem = getDefaultTheme(prefs.getTheme());
        new AlertDialog.Builder(requireActivity())
                .setTitle("Choose theme")
                .setSingleChoiceItems(items, checkedItem, null)
                .setPositiveButton("OK", (dialog, whichButton) -> {
                    dialog.dismiss();
                    int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                    switch (selectedPosition) {
                        case 1:
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            prefs.setTheme(1);
                            break;
                        case 2:
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            prefs.setTheme(2);
                            break;
                        case 0:
                        default:
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                            prefs.setTheme(-1);
                            break;
                    }
                })
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .show();
    }

    private int getDefaultTheme(int theme) {
        switch (theme) {
            case 1:
                return 1;
            case 2:
                return 2;
            case -1:
            default:
                return 0;
        }
    }
}