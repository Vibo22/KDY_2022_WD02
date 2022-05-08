package com.example.onlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CustomerTable extends AppCompatActivity {

    Button Reserve_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_table);
        Reserve_button = findViewById(R.id.Reserve_button);

        Reserve_button.setOnClickListener(view -> {
            Intent intent = new Intent(CustomerTable.this, Conform.class);
            startActivity(intent);
        });
    }
}