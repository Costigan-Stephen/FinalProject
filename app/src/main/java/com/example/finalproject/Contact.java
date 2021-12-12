package com.example.finalproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contact {
    public long id;
    public static String name;
    public long phone;
    public static String email;
    public String password;

    public Contact (String name, long phone, String email){
        this.id = System.currentTimeMillis();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public String getName () {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone(){
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Contact(String name, String email, String password){
        this.id = System.currentTimeMillis();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Contact(){

    }

}
