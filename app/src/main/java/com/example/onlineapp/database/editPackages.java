package com.example.onlineapp.database;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.onlineapp.List;
import com.example.onlineapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class editPackages extends AppCompatActivity {

    // variable declare
    
    Button choose,add;
    private static final int PICK_IMAGE_REQUEST=1;
    ImageView imgView;
    private Uri mImageUri;
    EditText name,des,price;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    ProgressBar progressBar;
    String img="",id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_packages);

        choose = findViewById(R.id.upImgBtn1);
        imgView = findViewById(R.id.viewImg1);
        add = findViewById(R.id.insertBtn1);
        name = findViewById(R.id.insertName1);
        des = findViewById(R.id.insertDescription1);
        price = (EditText)findViewById(R.id.editPrice);
        progressBar = findViewById(R.id.insertProgressBar1);

      // database connection
        
        mStorageRef= FirebaseStorage.getInstance().getReference("images");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("packages");

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        des.setText(intent.getStringExtra("des"));
        price.setText(intent.getStringExtra("price"));
        Glide.with(imgView.getContext()).load(intent.getStringExtra("image")).into(imgView);
        img=intent.getStringExtra("image");
        id=intent.getStringExtra("id");

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString()==""||name.getText().toString()==null){
                    Toast.makeText(editPackages.this, "Name is Required", Toast.LENGTH_SHORT).show();
                }else if(des.getText().toString()==""||des.getText().toString()==null){
                    Toast.makeText(editPackages.this, "Description is Required", Toast.LENGTH_SHORT).show();
                }else if(price.getText().toString()==""||price.getText().toString()==null){
                    Toast.makeText(editPackages.this, "Price is Required", Toast.LENGTH_SHORT).show();
                }else{
                    updateData();
                }
            }
        });

    }

    // Update packages details
    
    private void updateData() {

        if(img==""){
            uploadFile();
        }else {
            Packages packages = new Packages(name.getText().toString(), des.getText().toString(),Double.parseDouble(price.getText().toString()),img);

            mDatabaseRef.child(id).setValue(packages).addOnCompleteListener(
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            clearForm();
                            Toast.makeText(editPackages.this,"Update successful",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(editPackages.this, List.class);
                            startActivity(intent);
                        }
                    }
            );

        }

    }

    private void openFileChooser(){
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode ==RESULT_OK && data !=null && data.getData()!=null) {
            mImageUri=data.getData();
            Picasso.get().load(mImageUri).into(imgView);
            img="";
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private  void uploadFile(){
        if(mImageUri !=null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    +"." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler =new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);

                            if (taskSnapshot.getMetadata() != null) {
                                if (taskSnapshot.getMetadata().getReference() != null) {
                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            Packages upload=new Packages(name.getText().toString(),des.getText().toString(),Double.parseDouble(price.getText().toString()), uri.toString());
                                            mDatabaseRef.child(id).setValue(upload);
                                            clearForm();
                                            Toast.makeText(editPackages.this,"Upload successful",Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(editPackages.this,List.class);
                                            startActivity(intent);
                                            
                                        }
                                    });
                                }
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(editPackages.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0*taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });

        }else{
            Toast.makeText(this,"No File Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        name.setText("");
        des.setText("");
        price.setText("");
        imgView.setImageResource(R.drawable.logo);
        mImageUri=null;
    }
}
