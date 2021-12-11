package com.example.finalproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contact {
    public long id;
    public String name;
    public int phone;
    public String email;

    public Contact(String name, int phone, String email){
        this.id = System.currentTimeMillis();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

}
