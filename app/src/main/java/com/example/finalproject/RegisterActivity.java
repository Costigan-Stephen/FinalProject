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

    // Customer clicks to register account
    public void RegisterClick(View view)
    {
        // ensure no values are empty
        if (((TextView) findViewById(R.id.inputUser)).length() > 0 &&
                ((TextView) findViewById(R.id.inputEmail)).length() > 0 &&
                ((TextView) findViewById(R.id.inputPassword)).length() > 0 &&
                ((TextView) findViewById(R.id.inputConfirmPassword)).length() > 0) {
            // save password fields to ensure they match
            String pass = ((TextView) findViewById(R.id.inputPassword)).getText().toString();
            String conf = ((TextView) findViewById(R.id.inputConfirmPassword)).getText().toString();
//            System.out.println("Password:" + pass);
//            System.out.println("Password:" + conf);

            // Determine if passwords match
            if(pass.equals(conf)) {
                // save rest of variables
                String name = ((TextView) findViewById(R.id.inputUser)).getText().toString();
                String email = ((TextView) findViewById(R.id.inputEmail)).getText().toString();

                // create a new contact with provided details
                Contact contact = new Contact(name, email, pass);
                MainActivity main = new MainActivity();
                // register contact (user)
                main.registerClient(contact);
                // redirect to main activity
                startActivity(new Intent(this, MainActivity.class));
            } else {
                // passwords don't match.
                Toast toast = Toast.makeText(this, "Error: Passwords do not match!", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            // not all fields are filled in
            Toast toast = Toast.makeText(this, "Error: Please enter all required fields!", Toast.LENGTH_SHORT);
            toast.show();
        }




    }
}