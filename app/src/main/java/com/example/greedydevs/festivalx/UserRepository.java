package com.example.greedydevs.festivalx;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Paul on 11/18/2017.
 */

public class UserRepository {
    final FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
    DatabaseReference UsersRef;
    private String[]names;
    private int length = 0;

    public UserRepository()
    {
        names = new String[200];
        UsersRef = dataBase.getReference("users");
        UsersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int k =  0;
                for(DataSnapshot snap: dataSnapshot.getChildren())
                {
                    String usr = snap.child("name").getValue().toString();
                    names[length] = usr;
                    length+=1;
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    public int getLength()
    {
        return this.length;
    }

    public String getNameAt(int index)
    {
        return names[index];
    }
    public String[] getNames()
    {
        return names;
    }

    public boolean checkName(String name)
    {
        for(int i=0; i< this.length; i++)
        {
            if (name.equals(names[i]))
                return true;
        }
        return false;
    }
}

