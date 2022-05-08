package com.example.onlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {


    Button home;
    Button menu;
    Button table;
    Button offer;
    Button info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        home=findViewById(R.id.home);
        menu=findViewById(R.id.menu);
       table=findViewById(R.id.table);
       offer=findViewById(R.id.offer);
        info=findViewById(R.id.info);

        menu.setOnClickListener(view -> {
            Intent intent=new Intent(Dashboard.this,Menu.class);
            startActivity(intent);
        });

        offer.setOnClickListener(view -> {
            Intent intent=new Intent(Dashboard.this,MainPackage.class);
            startActivity(intent);
        });


    }
}