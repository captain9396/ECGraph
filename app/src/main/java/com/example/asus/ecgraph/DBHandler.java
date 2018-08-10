package com.example.asus.ecgraph;

import android.print.PrinterId;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBHandler {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String changedData;
    private static DataStorage dataStorage = DataStorage.getInstance();



    public DBHandler() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                changedData = dataSnapshot.getValue(String.class);
                dataStorage.setMyData(changedData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void writeData(String value){
        myRef.setValue(value);
    }

    public String getData() {

        return changedData;
    }


}
