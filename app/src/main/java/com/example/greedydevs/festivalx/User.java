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
    private String[] groupNames;
    private Group[] groups;
    private boolean hasGroups;

    private int groupNamesLength = 0;

    //groups part- latter on

    public User(String email)
    {
        groupNames = new String[200];
        groups = new Group[200];
        hasGroups = false;

        email = email.substring(0,email.length()-10);
        dbRef = dataBase.getReference("users" + '/' + email);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                setName( dataSnapshot.child("name").getValue().toString());
                money = Integer.valueOf(dataSnapshot.child("money").getValue().toString());
                if(dataSnapshot.child("groups").exists())
                {

                    for(int i =0; i < dataSnapshot.child("groups").getChildrenCount(); i++)
                    {
                        String grp = "group" + String.valueOf(i + 1);
                        groupNames[groupNamesLength] = dataSnapshot.child("groups").child(grp).getValue().toString();
                        groupNamesLength+=1;
                    }
                }
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
    public int getGroupsLength()
    {
        return this.groupNamesLength;
    }

    public void setGroups(GroupRepository repo)
    {
        for (int i=0; i<groupNamesLength;i++)
        {
            groups[i] = repo.getGroup(groupNames[i]);
        }
    }

}
