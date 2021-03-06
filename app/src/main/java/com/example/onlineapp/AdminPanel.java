package com.example.onlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.onlineapp.database.MainPackage;
import com.example.onlineapp.database.MenuAdd;

public class AdminPanel extends AppCompatActivity {

    private Button PackageAdmin;
    private Button MenuAdmin;
    private Button TableAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);



        PackageAdmin = (Button) findViewById(R.id.breakfast);
        PackageAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, MainPackage.class);
                //intent.putExtra("Package Admin", "addPackages");
                startActivity(intent);
            }
        });

        MenuAdmin = (Button) findViewById(R.id.lunch);
        MenuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, MenuAdd.class);
                //intent.putExtra("Menu Admin", "Menu");
                startActivity(intent);
            }
        });
/*
        TableAdmin = (Button) findViewById(R.id.Table_btn);
        TableAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, Table.class);
                intent.putExtra("Table Admin", "Table");
                startActivity(intent);
            }
        });*/


    }
}