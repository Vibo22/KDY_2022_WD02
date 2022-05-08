package com.example.onlineapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class PostAdapterCustomer extends FirebaseRecyclerAdapter<PostCustomer,PostAdapterCustomer.ViewHolder1> {

    public PostAdapterCustomer(@NonNull FirebaseRecyclerOptions<PostCustomer> options, Recyclerviewcustomer recyclerviewcustomer) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostAdapterCustomer.ViewHolder1 holder, @SuppressLint("RecyclerView") final int position, @NonNull final PostCustomer model) {

        holder.tvmenuname.setText(model.getMenuName());
        holder.tvmenuprice.setText(model.getMenuPrice());


        String imageUri=model.getImage();
        Picasso.get().load(imageUri).into(holder.imageAdd);

    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.design_row_for_customer_recyclerview,parent,false);

        return new ViewHolder1(view);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {


        TextView tvmenuname,tvmenuprice;
        ImageView imageAdd;


        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            tvmenuname=itemView.findViewById(R.id.tv_menunameRetrive);
            tvmenuprice=itemView.findViewById(R.id.tv_menupriceRetrive);
            imageAdd=itemView.findViewById(R.id.image_ViewCourse);



        }
    }
}



