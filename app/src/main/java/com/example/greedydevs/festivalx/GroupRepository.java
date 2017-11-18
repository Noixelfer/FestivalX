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
    private Group[] groups;
    private int len = 0;

    public GroupRepository() {
        groups = new Group[200];
        GroupsRef = dataBase.getReference("Groups");
        GroupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String groupName = snap.getKey().toString();
                    String[] users = new String[200];
                    String[] products = new String[200];
                    int length = (int) snap.getChildrenCount();
                    if (snap.child("products").exists()) {
                        length -= 1;
                        for (int j = 0; j < snap.child("products").getChildrenCount(); j++) {
                            String prod = "prod" + String.valueOf(j + 1);
                            products[j] = snap.child("products").child(prod).getValue().toString();
                        }
                    }
                    for (int i = 0; i < length; i++) {
                        String member = "member" + String.valueOf(i + 1);
                        users[i] = snap.child(member).getValue().toString();
                    }
                    Group g = new Group(groupName, users, products);
                    groups[len] = g;
                    len += 1;
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    public boolean groupExist(String name) {
        for (int i = 0; i < len; i++) {
            if (groups[i].getName().equals(name))
                return true;
        }
        return false;
    }

    public int getLength() {
        return len;
    }

    public Group getGroup(String name) {
        for (int i = 0; i < len; i++) {
            if (groups[i].getName().equals(name))
                return groups[i];
        }
        return null;
    }

    public Group[] getGroups() {
        return groups;
    }

}
