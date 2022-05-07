package com.example.onlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {

    private Button PackageAdmin;
    private Button MenuAdmin;
    private Button TableAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Paper.init(this);

        PackageAdmin = (Button) findViewById(R.id.addpacakage_btn);
        PackageAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, addPackages.class);
                intent.putExtra("Package Admin", "addPackages");
                startActivity(intent);
            }
        });

        MenuAdmin = (Button) findViewById(R.id.Menu_btn);
        MenuAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, Menu.class);
                intent.putExtra("Menu Admin", "Menu");
                startActivity(intent);
            }
        });

        TableAdmin = (Button) findViewById(R.id.Table_btn);
        TableAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, Table.class);
                intent.putExtra("Table Admin", "Table");
                startActivity(intent);
            }
        });


    }
}