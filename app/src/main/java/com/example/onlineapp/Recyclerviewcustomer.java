package com.example.onlineapp;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Recyclerviewcustomer extends AppCompatActivity {

    RecyclerView recyclerView1;
    com.example.onlineapp.PostAdapterCustomer adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerviewcustomer);




        recyclerView1 = findViewById(R.id.recyclerview_id1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<com.example.onlineapp.PostCustomer> options =
                new FirebaseRecyclerOptions.Builder<com.example.onlineapp.PostCustomer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Menu"), com.example.onlineapp.PostCustomer.class)
                        .build();


        adapter1 = new com.example.onlineapp.PostAdapterCustomer(options, this);
        recyclerView1.setAdapter(adapter1);

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
    }

}