package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

    }

    public void SignInClick(View view){
        // Check login info
        startActivity(new Intent(this, MainActivity.class));
    }

    public void registerClick(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }
}