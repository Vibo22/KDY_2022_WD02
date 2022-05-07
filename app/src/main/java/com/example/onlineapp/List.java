package com.example.onlineapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class List extends AppCompatActivity implements List_View.FragmentAListener{

    private List_View list_view;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        list_view = new List_View();

        getSupportFragmentManager().beginTransaction().replace(R.id.container_list_view , list_view).commit();

    }

    @Override
    public void onInputASent(CharSequence input) {

    }
}