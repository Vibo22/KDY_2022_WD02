package com.example.onlineapp.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PostAdapter extends FirebaseRecyclerAdapter<PostModel,PostAdapter.ViewHolder> {

    private Context context;
    public PostAdapter(@NonNull FirebaseRecyclerOptions<PostModel> options, Context context) {
        super(options);

        this.context=context;
    }
    // delete data
    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final PostModel model) {

        holder.tvmenuid.setText(model.getMenuId());
        holder.tvmenuname.setText(model.getMenuName());
        holder.tvmenuprice.setText(model.getMenuPrice());


        String imageUri=model.getImage();
        Picasso.get().load(imageUri).into(holder.imageAdd);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Menu")
                        .child(getRef(position).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        //update data
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialog= DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.content))
                        .setExpanded(false)
                        .create();

                View holderView=dialog.getHolderView();

                final EditText id=holderView.findViewById(R.id.update_id);
                final EditText name=holderView.findViewById(R.id.update_name);
                final EditText price=holderView.findViewById(R.id.update_price);
                Button btnUpdate=holderView.findViewById(R.id.btnupdate);

                //set values
                id.setText(model.getMenuId());
                name.setText(model.getMenuName());
                price.setText(model.getMenuPrice());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map=new HashMap<>();

                        map.put("menuName",name.getText().toString());
                        map.put("menuPrice",price.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Menu")
                                .child(getRef(position).getKey())
                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                dialog.dismiss();
                            }
                        });

                    }
                });

                dialog.show();


            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design_row_for_recyclerview,parent,false);


        return new ViewHolder(view);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvmenuid,tvmenuname,tvmenuprice;
        ImageView imageAdd,update,delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvmenuid=itemView.findViewById(R.id.tv_menuIdRetrive);
            tvmenuname=itemView.findViewById(R.id.tv_menunameRetrive);
            tvmenuprice=itemView.findViewById(R.id.tv_menupriceRetrive);
            imageAdd=itemView.findViewById(R.id.image_ViewCourse);
            update=itemView.findViewById(R.id.image_edit);
            delete=itemView.findViewById(R.id.image_delete);



        }
    }
}
