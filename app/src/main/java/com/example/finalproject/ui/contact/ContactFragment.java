package com.example.finalproject.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject.databinding.FragmentIndContactBinding;
import com.example.finalproject.databinding.FragmentIndMessageBinding;
import com.example.finalproject.ui.contact.ContactViewModel;

public class ContactFragment extends Fragment {

    private ContactViewModel contactViewModel;
    private FragmentIndContactBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        binding = FragmentIndContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}