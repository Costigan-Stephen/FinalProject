package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

        if (((TextView) findViewById(R.id.inputUser)).length() > 0 &&
                ((TextView) findViewById(R.id.inputEmail)).length() > 0 &&
                ((TextView) findViewById(R.id.inputPassword)).length() > 0 &&
                ((TextView) findViewById(R.id.inputConfirmPassword)).length() > 0) {
            // Save Values
            String pass = ((TextView) findViewById(R.id.inputPassword)).getText().toString();
            String conf = ((TextView) findViewById(R.id.inputConfirmPassword)).getText().toString();
            System.out.println("Password:" + pass);
            System.out.println("Password:" + conf);

            if(pass.equals(conf)) {
                String name = ((TextView) findViewById(R.id.inputUser)).getText().toString();
                String email = ((TextView) findViewById(R.id.inputEmail)).getText().toString();

                Contact contact = new Contact(name, email, pass);
                MainActivity main = new MainActivity();
                main.registerClient(contact);
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast toast = Toast.makeText(this, "Error: Passwords do not match!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }




    }
}