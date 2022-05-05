package com.example.onlineapp;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class lunch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
    }

    public void yellowrice1(View view) {
        Intent i =new Intent(this,yellowrice1.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void cuminwhite(View view) {
        Intent i =new Intent(this,cuminwhite.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void garlic(View view) {
        Intent i =new Intent(this,garlic.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void yellowrice2(View view) {
        Intent i =new Intent(this,yellowrice2.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

    public void rice1(View view) {
        Intent i =new Intent(this,rice1.class);
        Bundle b= makeSceneTransitionAnimation(this).toBundle();
        startActivity(i,b);
    }

}