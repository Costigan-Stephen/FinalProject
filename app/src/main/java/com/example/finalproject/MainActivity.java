package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.finalproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<Contact> contacts = new LinkedList<>();
    private final LinkedList<Messages> messages = new LinkedList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final AtomicInteger count = new AtomicInteger();
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

        // Firebase sync
        messageRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numChildren = dataSnapshot.getChildrenCount();
                System.out.println(count.get() + " == " + numChildren);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        // MUST BE LAST IN ONCREATE
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    // User clicks on profile button inside of Settings
    public void clickProfile(View view){
        navController.navigate(R.id.navigation_profile);
    }

    // User clicks on message list on nav bar
    public void backMessageList(View view){ navController.navigate(R.id.navigation_messages); }

    // User clicks back button while in contact view
    public void backContactList(View view){ navController.navigate(R.id.navigation_contacts); }

    // User clicks back button while in the individual contact. Previous is used to determine whether
    // user's previous layout was the profile, new user, or editing an existing user.
    public void backContactInd(View view){ navController.navigate(previous); }

    // Send new message, reached from messagelist view
    public void clickMessage(View view){
        navController.navigate(R.id.navigation_ind_message);
    }

    // click contact from contact list. Not implemented yet as there is no list of contacts
    public void clickContact(View view){
        //currentContact value would be set here
        navController.navigate(R.id.navigation_ind_contact);
    }

    // From within contact ind view, clicking on edit will return the edit layout
    public void clickEditContact(View view){
        previous = R.id.navigation_ind_contact;
        next = previous;
        editMode = true;
        profile = false;
        navController.navigate(R.id.navigation_edit_contact);
    }

    // User clicks to edit their profile, which goes to the edit contact layout
    public void clickEditProfile(View view){
        previous = R.id.navigation_profile;
        next = previous;
        editMode = true;
        profile = true;
        navController.navigate(R.id.navigation_edit_contact);
    }

    // gather information from edit layout to determine whether required fields are filled in. Save contact to firebase
    public void clickSaveContact(View view){
            // do fields have values in them? If not, go no further.
            if (((TextView) findViewById(R.id.text_name)).length() > 0 &&
                    ((TextView) findViewById(R.id.text_phone)).length() > 0 &&
                    ((TextView) findViewById(R.id.text_email)).length() > 0) {
                // Save Values to variables
                String name = ((TextView) findViewById(R.id.text_name)).getText().toString();
                long phone = Integer.parseInt(((TextView) findViewById(R.id.text_phone)).getText().toString());
                String email = ((TextView) findViewById(R.id.text_email)).getText().toString();

                // post variables to a contact constructor
                Contact contact = new Contact(name, phone, email);
                // add contact to list of existing contacts (intended to have been pulled)
                contacts.add(contact);

                if(!editMode)
                    currentContact = contact.id;

                // Save user to firebase
                addContactToFirebase();

                // Display text based on which layout is the return layout (next).
                Toast toast = Toast.makeText(this, getSaveText(), Toast.LENGTH_SHORT);
                toast.show();
                navController.navigate(next);

                // Intention: set text on layout to enteted values. Didn't get time to implement correctly
//                ((TextView) findViewById(R.id.textView8)).setText("Name: " + name);
//                ((TextView) findViewById(R.id.textView10)).setText("Phone: " + String.valueOf(phone));
//                ((TextView) findViewById(R.id.textView9)).setText("Email: " + email);
            } else {
                // Display error message
                Toast toast = Toast.makeText(this, "Error: Please enter all required fields!", Toast.LENGTH_SHORT);
                toast.show();
            }
    }

    // If the button is pressed to add a new contact, go to edit contact layout
    public void clickAddContact(View view){
        previous = R.id.navigation_contacts;
        next = R.id.navigation_ind_contact;
        editMode = false;
        profile = false;
        navController.navigate(R.id.navigation_edit_contact);
    }

    // depending on (next) value, return string based on boolean values of editmode and profile.
    public String getSaveText(){
        return editMode ? ( profile ? "Profile updated successfully!" : "Contact updated successfully!") : "Contact created successfully!";
    }

    // Post contacts to firebase
    private void addContactToFirebase() {
        try {
            // Retrieve contacts from firebase, to determine if any have been added since
            getContacts();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Save contacts to firebase
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

    // Gather contacts from the server and update local variable
    private void getContacts() throws IOException {
      /*  // asynchronously retrieve the document
                ApiFuture<DocumentSnapshot> future = usersRef.get();
        // block on response
                DocumentSnapshot document = future.get();
                City city = null;
                if (document.exists()) {
                    // convert document to POJO
                    city = document.toObject(City.class);
                    System.out.println(city);
                } else {
                    System.out.println("No such document!");
                }
*/
    }

    // Add individual message to firebase
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

    // Take registered customer and save them to contacts
    public void registerClient(Contact contact) {
        usersRef = database.getReference("Contacts");
        contacts.add(contact);
        addContactToFirebase();
    }

    // Retrieve data from firebase for use in the application
    public void getInfo(View view) throws IOException {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/Contacts/0");
        System.out.println("Here");

//        URL url = new URL("https://samchatapp-1b61e-default-rtdb.firebaseio.com/Contacts");
//        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//        httpURLConnection.setRequestMethod("GET");
//        httpURLConnection.setRequestProperty("Contacts",);
//        int responseCode = httpURLConnection.getResponseCode();
//        System.out.println("GET Response Code :: " + responseCode);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Contact post = dataSnapshot.getValue(Contact.class);
                System.out.println(post.name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}