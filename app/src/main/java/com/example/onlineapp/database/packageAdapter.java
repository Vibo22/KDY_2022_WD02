// Package adapter file

package com.example.onlineapp.database;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class packageAdapter extends FirebaseRecyclerAdapter<Packages,packageAdapter.myviewholder>
{
    public packageAdapter(@NonNull FirebaseRecyclerOptions<Packages> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull Packages packages)
    {
       holder.name.setText(packages.getName());
       holder.des.setText(packages.getDescription());
       holder.price.setText(packages.getPrice().toString());
       System.out.println(packages.getImageUrl());
       Glide.with(holder.img.getContext()).load(packages.getImageUrl()).into(holder.img);

                    holder.edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(view.getContext(), editPackages.class);
                            i.putExtra("name", packages.getName() );
                            i.putExtra("des", packages.getDescription() );
                            i.putExtra("price", packages.getPrice().toString() );
                            i.putExtra("image", packages.getImageUrl());
                            i.putExtra("id", getRef(position).getKey());
                            view.getContext().startActivity(i);
                        }
                    });


                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                            builder.setTitle("Delete Packages");
                            builder.setMessage("Delete...?");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    FirebaseDatabase.getInstance().getReference().child("packages")
                                            .child(getRef(position).getKey()).removeValue();
                                }
                            });

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                            builder.show();
                        }
                    });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        ImageView edit,delete;
        TextView name,des,price;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            des=(TextView)itemView.findViewById(R.id.descriptiontext);
            price=(TextView)itemView.findViewById(R.id.pricetext);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
