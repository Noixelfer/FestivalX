package com.example.greedydevs.festivalx;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Paul on 11/18/2017.
 */

public class GroupRepository {
    final FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
    DatabaseReference GroupsRef;
    public GroupRepository()
    {
        GroupsRef = dataBase.getReference("Groups");
            GroupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren())
                {
                    if(snap.child("products").exists())
                    {
                        
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

}
