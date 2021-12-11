package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.finalproject.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<Contact> contacts = new LinkedList<>();
    private final LinkedList<Messages> messages = new LinkedList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef;
    DatabaseReference messageRef;
    private ActivityMainBinding binding;
    public BottomNavigationView navView;
    public NavController navController;
    public boolean editMode;
    public boolean profile;
    int previous;
    int next;
    long currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersRef = database.getReference("Contacts");
        messageRef = database.getReference("Message");
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

    public void backContactInd(View view){ navController.navigate(previous); }

    public void clickMessage(View view){
        navController.navigate(R.id.navigation_ind_message);
    }

    // click contact from list
    public void clickContact(View view){
        //currentContact
        navController.navigate(R.id.navigation_ind_contact);
    }

    public void clickEditContact(View view){
        previous = R.id.navigation_ind_contact;
        next = previous;
        editMode = true;
        profile = false;
        navController.navigate(R.id.navigation_edit_contact);
    }

    public void clickEditProfile(View view){
        previous = R.id.navigation_profile;
        next = previous;
        editMode = true;
        profile = true;
        navController.navigate(R.id.navigation_edit_contact);
    }

    public void clickSaveContact(View view){
            if (((TextView) findViewById(R.id.text_name)).length() > 0 &&
                    ((TextView) findViewById(R.id.text_phone)).length() > 0 &&
                    ((TextView) findViewById(R.id.text_email)).length() > 0) { // Save Values
                String name = ((TextView) findViewById(R.id.text_name)).length() > 0 ? ((TextView) findViewById(R.id.text_name)).getText().toString() : "";
                int phone = Integer.parseInt(((TextView) findViewById(R.id.text_phone)).length() > 0 ? ((TextView) findViewById(R.id.text_phone)).getText().toString() : "");
                String email = ((TextView) findViewById(R.id.text_email)).length() > 0 ? ((TextView) findViewById(R.id.text_email)).getText().toString() : "";

                Contact contact = new Contact(name, phone, email);
                contacts.add(contact);
                currentContact = contact.id;

//                DatabaseReference ref = database.getReference("https://samchatapp-1b61e-default-rtdb.firebaseio.com/");
//                usersRef = ref.child("contact");
//                usersRef.setValue(contacts);

                addContactToFirebase();

                Toast toast = Toast.makeText(this, getSaveText(), Toast.LENGTH_SHORT);
                toast.show();
                navController.navigate(next);
            } else {
                Toast toast = Toast.makeText(this, "Error: Please enter all required fields!", Toast.LENGTH_SHORT);
                toast.show();
            }


    }

    public void clickAddContact(View view){
        previous = R.id.navigation_contacts;
        next = R.id.navigation_ind_contact;
        editMode = false;
        profile = false;
        navController.navigate(R.id.navigation_edit_contact);
    }

    public String getSaveText(){
        return editMode ? ( profile ? "Profile updated successfully!" : "Contact updated successfully!") : "Contact created successfully!";
    }

    private void addContactToFirebase() {

        usersRef.setValue(contacts, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });
    }

    private void addMessagetoFirebase() {

        messageRef.setValue(messages, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });
    }

}