package com.example.finalproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contact {
    public long id;
    public String name;
    public long phone;
    public String email;
    public String password;

    public Contact(String name, long phone, String email){
        this.id = System.currentTimeMillis();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Contact(String name, String email, String password){
        this.id = System.currentTimeMillis();
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
