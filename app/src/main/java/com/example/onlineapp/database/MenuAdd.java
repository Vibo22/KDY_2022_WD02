package com.example.onlineapp.database;

import static android.app.ActivityOptions.makeSceneTransitionAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.onlineapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MenuAdd extends AppCompatActivity {

    ImageButton imageButton;
    EditText edtmenuid, edtmenuname, edtmenuprice;
    Button btnaddmenu, btnshowmenu,btnmenucount;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    StorageReference mStorage;
    private static final int Gallery_code = 1;
    private Uri imageUri = null;
    ProgressDialog mprograss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);

        imageButton = findViewById(R.id.btn_courseImagBtn);
        edtmenuid = findViewById(R.id.edt_MenuId);
        edtmenuname = findViewById(R.id.edt_MenuName);
        edtmenuprice = findViewById(R.id.edt_MenuPrice);


        mprograss = new ProgressDialog(this);

        btnaddmenu = findViewById(R.id.btn_addMenu);
        btnshowmenu = findViewById(R.id.btn_showMenu);
        btnmenucount=findViewById(R.id.btn_Menucount);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Menu");
        mStorage = FirebaseStorage.getInstance().getReference();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_code);
            }

        });

    }

    //insert data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery_code && resultCode == RESULT_OK) {
            imageUri = data.getData();
            imageButton.setImageURI(imageUri);
        }
        btnaddmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = edtmenuid.getText().toString().trim();
                String name = edtmenuname.getText().toString().trim();
                String price = edtmenuprice.getText().toString().trim();

                if (!id.isEmpty() && !name.isEmpty() && !price.isEmpty() && imageUri != null) {

                    mprograss.setMessage("uploading...");
                    mprograss.show();

                    StorageReference filepath = mStorage.child("image_post").child(imageUri.getLastPathSegment());
                    filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t = task.getResult().toString();
                                    DatabaseReference newPost = mRef.push();
                                    newPost.child("menuId").setValue(id);
                                    newPost.child("menuName").setValue(name);
                                    newPost.child("menuPrice").setValue(price);
                                    newPost.child("image").setValue(task.getResult().toString());

                                    mprograss.dismiss();
                                    Toast.makeText(MenuAdd.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MenuAdd.this, RecyclerviewList.class);
                                    startActivity(intent);

                                }
                            });
                        }
                    });


                }
                ;
            }
        });


    }

    public void btnShowMenu(View view) {
        Intent i = new Intent(this, Recyclerviewcustomer.class);
        Bundle b = makeSceneTransitionAnimation(this).toBundle();
        startActivity(i, b);

    }

    public void Btnmenucount(View view){
        Intent i=new Intent(this, MenuCount.class);
        startActivity(i);
    }
}


















