package com.example.finalproject;

import java.util.ArrayList;

public class Messages {
    private static String mName;
    private boolean mOnline;

    public Messages(String name, boolean online) {
        mName = name;
        mOnline = online;
    }
    public static String getName() {
        return mName;
    }


    private static int lastMessageId = 0;

    public static ArrayList<Messages> createMessageList(int numMessages) {
        ArrayList<Messages> contacts = new ArrayList<Messages>();

        for (int i = 1; i <= numMessages; i++) {
            contacts.add(new Messages("Person " + ++lastMessageId, i <= numMessages / 2));
        }

        return contacts;
    }

}
