package com.example.onlineapp;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button button;
    Button button2;
    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        button=findViewById(R.id.breakfast);
        button2=findViewById(R.id.lunch);
        button3=findViewById(R.id.dinner);

        button.setOnClickListener(view -> {
            Intent intent=new Intent(Menu.this,breakfast.class);
            startActivity(intent);
        });

        button2.setOnClickListener(view -> {
            Intent intent=new Intent(Menu.this,lunch.class);
            startActivity(intent);
        });

        button3.setOnClickListener(view -> {
            Intent intent=new Intent(Menu.this,dinner.class);
            startActivity(intent);
        });

    }


    public void breakfast(View view) {
        Intent i =new Intent(this,breakfast.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void lunch(View view) {
        Intent i =new Intent(this,lunch.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void dinner(View view) {
        Intent i =new Intent(this,dinner.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

}