package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.finalproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public BottomNavigationView navView;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_contacts,
                R.id.navigation_messages,
                R.id.navigation_settings,
                R.id.navigation_profile,
                R.id.navigation_ind_message,
                R.id.navigation_ind_contact,
                R.id.navigation_edit_contact)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }

    public void clickProfile(View view){
        navController.navigate(R.id.navigation_profile);
    }

    public void backMessageList(View view){ navController.navigate(R.id.navigation_messages); }

    public void backContactList(View view){ navController.navigate(R.id.navigation_contacts); }

    public void clickMessage(View view){
        navController.navigate(R.id.navigation_ind_message);
    }

    public void clickContact(View view){ navController.navigate(R.id.navigation_ind_contact); }

    public void clickEditContact(View view){ navController.navigate(R.id.navigation_edit_contact); }

    public void clickSaveContact(View view){ navController.navigate(R.id.navigation_ind_contact); }

}