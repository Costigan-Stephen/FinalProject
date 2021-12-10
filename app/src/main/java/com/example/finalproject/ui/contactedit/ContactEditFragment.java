package com.example.finalproject.ui.contactedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject.databinding.FragmentEditContactBinding;
import com.example.finalproject.ui.contactedit.ContactEditViewModel;

public class ContactEditFragment extends Fragment {

    private ContactEditViewModel contactEditViewModel;
    private FragmentEditContactBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactEditViewModel = new ViewModelProvider(this).get(ContactEditViewModel.class);

        binding = FragmentEditContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}