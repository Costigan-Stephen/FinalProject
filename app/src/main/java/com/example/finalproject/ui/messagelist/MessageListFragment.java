package com.example.finalproject.ui.messagelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject.databinding.FragmentMessagelistBinding;

public class MessageListFragment extends Fragment {

    private MessageListViewModel homeViewModel;
    private FragmentMessagelistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(MessageListViewModel.class);

        binding = FragmentMessagelistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final RecyclerView textView = binding.recyclerView;
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}