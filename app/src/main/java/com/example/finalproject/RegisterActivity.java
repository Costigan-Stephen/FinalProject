package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void returnToLoginClick(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void RegisterClick(View view)
    {
        // Store user info
        startActivity(new Intent(this, MainActivity.class));
    }
}