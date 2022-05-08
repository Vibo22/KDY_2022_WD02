package com.example.onlineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuCount extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef, menuRef;
    TextView MenuCount;

    private int countMenu = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_count);


        MenuCount = findViewById(R.id.tv_menuCount);

        mDatabase = FirebaseDatabase.getInstance();
        menuRef = mDatabase.getReference().child("Menu");

        menuRef.addValueEventListener(new ValueEventListener() {

            //calculate Menu count
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    countMenu = (int)dataSnapshot.getChildrenCount();
                    MenuCount.setText(Integer.toString(countMenu));

                }
                else{
                    MenuCount.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
