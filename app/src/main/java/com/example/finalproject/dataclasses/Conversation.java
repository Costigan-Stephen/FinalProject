package com.example.finalproject.dataclasses;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Conversation {
    private String conversationId;
    private ArrayList<String> recipients;  // Replace String with Contact/User ID
    private String sender;              // Replace String with Contact/User ID
    private Timestamp last_sent;

    public Conversation(ArrayList<String> recipients, String sender){
        this.recipients = recipients;
        this.sender = sender;
    }

    public void AddRecipients(String sender){
        this.recipients.add(sender);
    }

    public ArrayList<String> GetRecipients(){
        return recipients;
    }

    public Timestamp getLastsent(){
        return last_sent;
    }

    public Timestamp setLastSent(Timestamp time){
        return this.last_sent = time;
    }

}
