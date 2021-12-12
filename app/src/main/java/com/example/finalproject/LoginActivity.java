package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

    }
    // When user clicks on the Sign In button, perform this task
    public void LogInClick(View view){
        // Check login info
        startActivity(new Intent(this, MainActivity.class));

        // Store user info

        if (((TextView) findViewById(R.id.inputPassword)).length() > 0 &&
                ((TextView) findViewById(R.id.inputEmail)).length() > 0 ) {
            // Save Values
            String pass = ((TextView) findViewById(R.id.inputPassword)).getText().toString();
            String email = ((TextView) findViewById(R.id.inputEmail)).getText().toString();
            System.out.println("Password:" + pass);
            System.out.println("Email:" + email);

            // Validate login (not implemented yet)
            if (true) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast toast = Toast.makeText(this, "Error: invalid credentials", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    // User clicks on register button
    public void registerClick(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }
}