package com.example.onlineapp.database;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.onlineapp.R;
import com.example.onlineapp.database.PostAdapterCustomer;
import com.example.onlineapp.database.PostCustomer;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Recyclerviewcustomer extends AppCompatActivity {

    RecyclerView recyclerView1;
    PostAdapterCustomer adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerviewcustomer);




        recyclerView1 = findViewById(R.id.recyclerview_id1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PostCustomer> options =
                new FirebaseRecyclerOptions.Builder<PostCustomer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Menu"), PostCustomer.class)
                        .build();


        adapter1 = new PostAdapterCustomer(options, this);
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