package com.example.greedydevs.festivalx;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Paul on 11/17/2017.
 */

public class User  {
    final FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
    DatabaseReference dbRef;
    private String userName;
    private int money;
    private String[] friendList;
    //groups part- latter on

    public User(String email)
    {
        email = email.substring(0,email.length()-10);
        dbRef = dataBase.getReference("users" + '/' + email);
        int a = 20;
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                setName( dataSnapshot.child("name").getValue().toString());
                money = Integer.valueOf(dataSnapshot.child("money").getValue().toString());
                //the code for friendList will be added later
                friendList = new String[100];
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    public String getName()
    {
        return this.userName;
    }
    public void setName(String name) {
        this.userName = name;
    }

    public int getMoney()
    {
        return this.money;
    }

}
