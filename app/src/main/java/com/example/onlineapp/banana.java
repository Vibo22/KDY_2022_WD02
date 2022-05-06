package com.example.onlineapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class banana extends AppCompatActivity {


    String[] items={"01","02","05","Other"};

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banana);

        autoCompleteTextView=findViewById(R.id.auto_complete_text);
        adapterItems=new ArrayAdapter<String >(this,R.layout.list_item,items);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),""+item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showAlert(View v){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setMessage("Do you want to confirm the order?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(banana.this, "Confirm order", Toast.LENGTH_SHORT).show();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(banana.this, "Cancel order", Toast.LENGTH_SHORT).show();
            }
        });

        alert.create().show();
    }

}