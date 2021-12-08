package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.adapter.MessageItemAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Contacts extends Fragment {

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        // ...
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Lookup the recyclerview in activity layout
//        //RecyclerView rvMessages = (RecyclerView) findViewById(R.id.rvMessages);
//
//        // Initialize contacts
////        messages = Messages.createMessageList(20);
////        // Create adapter passing in the sample user data
////        MessageItemAdapter adapter = new MessageItemAdapter(messages);
////        // Attach the adapter to the recyclerview to populate items
////        rvMessages.setAdapter(adapter);
////        // Set layout manager to position the items
////        rvMessages.setLayoutManager(new LinearLayoutManager(this));
//        // That's all!
//
//    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
//        TextView output = (TextView)view.findViewById(R.id.msg2);
//        output.setText("Fragment Two");
        return view;
    }

}