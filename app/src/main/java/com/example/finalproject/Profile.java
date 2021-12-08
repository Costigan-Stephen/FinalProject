package com.example.finalproject;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Profile extends FragmentActivity {

    private ProfileViewModel mViewModel;

    public static Profile newInstance() {
        return new Profile();
    }


}