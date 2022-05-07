package com.example.onlineapp;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
    }

    public void banana(View view) {
        Intent i =new Intent(this,banana.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void yellowrice3(View view) {
        Intent i =new Intent(this,yellowrice3.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void whiterice(View view) {
        Intent i =new Intent(this,whiterice.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void cashew(View view) {
        Intent i =new Intent(this,cashew.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void special(View view) {
        Intent i =new Intent(this,special.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

}