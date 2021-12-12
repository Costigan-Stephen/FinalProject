package com.example.finalproject.ui.contactedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentEditContactBinding;

public class ContactEditFragment extends Fragment {

    private ContactEditViewModel contactEditViewModel;
    private FragmentEditContactBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactEditViewModel = new ViewModelProvider(this).get(ContactEditViewModel.class);

        binding = FragmentEditContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        root = inflater.inflate(R.layout.fragment_edit_contact, container, false);

        // base navigation around what boolean values are stored in main
        if(((MainActivity)getActivity()).editMode) {
            // Change text and button based on which is true
            if(((MainActivity)getActivity()).profile) {
                // editing user
                ((TextView) root.findViewById(R.id.edit_contact)).setText("Edit Profile");
                ((Button) root.findViewById(R.id.button2)).setText("Update Profile");
            } else {
                // editing contact
                ((TextView) root.findViewById(R.id.edit_contact)).setText("Edit Contact");
                ((Button) root.findViewById(R.id.button2)).setText("Update Contact");
            }
        } else {
            // creating a new contact
            ((TextView) root.findViewById(R.id.edit_contact)).setText("New Contact");
            ((Button) root.findViewById(R.id.button2)).setText("Save Contact");
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}