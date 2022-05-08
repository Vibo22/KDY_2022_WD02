package com.example.onlineapp;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecyclerviewList extends AppCompatActivity {

    RecyclerView recyclerView;
    com.example.crud_activity.PostAdapter adapter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_list);

        recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<com.example.crud_activity.PostModel> options =
                new FirebaseRecyclerOptions.Builder<com.example.crud_activity.PostModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Menu"), com.example.crud_activity.PostModel.class)
                        .build();

        adapter = new com.example.crud_activity.PostAdapter(options, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}


