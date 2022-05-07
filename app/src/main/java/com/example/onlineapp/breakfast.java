package com.example.onlineapp;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class breakfast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
    }


    public void roti(View view) {
        Intent i =new Intent(this,roti.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void pittu(View view) {
        Intent i =new Intent(this,pittu.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void idiappa(View view) {
        Intent i =new Intent(this,idiappa.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void kiribath(View view) {
        Intent i =new Intent(this,kiribath.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void rathu(View view) {
        Intent i =new Intent(this,rathu.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }



}