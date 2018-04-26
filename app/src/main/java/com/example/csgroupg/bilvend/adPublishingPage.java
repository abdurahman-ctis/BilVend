package com.example.csgroupg.bilvend;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class adPublishingPage extends AppCompatActivity{

    private final String[] CATEGORIES = {"Categories", "Books", "Notes","Household","Electronic devices", "Roommate","Others"};
    private final int REQUEST_CAMERA = 1;
    private final int SELECT_FILE = 0;

    private ImageButton publish;
    private FloatingActionButton camera;
    private ImageView imageOfItem;
    private EditText nameOfItem;
    private EditText priceOfItem;
    private EditText description;
    private View mDividerMode;
    private ArrayList<EditText> list;
    private Advertisement mItem;
    private Spinner spinner;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mReference;
    private int  ID;
    private StorageReference mStorageRef;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_publishing_page);
        Toolbar toolbar = findViewById(R.id.toolbar);

        mAuth =FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference().child("Advertisements");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ID = Integer.parseInt(String.valueOf(dataSnapshot.child("Total number of advertisements").getValue(Long.class))) + 1;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });


        publish = findViewById(R.id.finishPublish);
        camera = findViewById(R.id.camera);
        imageOfItem = findViewById(R.id.photo);
        nameOfItem = findViewById(R.id.title);
        priceOfItem = findViewById(R.id.price);
        description = findViewById(R.id.description);
        mDividerMode = findViewById(R.id.divider);
        list = new ArrayList<>();
        list.add(nameOfItem);
        list.add(priceOfItem);
        list.add(description);
        setSupportActionBar(toolbar);

        // configure ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,CATEGORIES);
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);

        // set item selection to spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 4 && position != 0)
                {
                    imageOfItem.setVisibility(View.VISIBLE);
                    camera.setVisibility(View.VISIBLE);
                    nameOfItem.setVisibility(View.VISIBLE);
                    priceOfItem.setVisibility(View.VISIBLE);
                    description.setVisibility(View.VISIBLE);
                    mDividerMode.setVisibility(View.VISIBLE);
                    camera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectImage();
                        }
                    });
                }
                if (position == 0)
                {
                    imageOfItem.setVisibility(View.INVISIBLE);
                    camera.setVisibility(View.INVISIBLE);
                    nameOfItem.setVisibility(View.INVISIBLE);
                    priceOfItem.setVisibility(View.INVISIBLE);
                    description.setVisibility(View.INVISIBLE);
                    mDividerMode.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"asdasd",Toast.LENGTH_SHORT).show();
            }
        });

        // click event listener to finish publishing
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (checkFilled(list))
               {
                   publishAdvertisement();
                   startActivity(new Intent(getApplicationContext(),mainPage.class));
               }
            }
        });
    }
    public boolean checkFilled(ArrayList<EditText> list)
    {
        for(EditText e : list)
        {
            if(e.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Please fully enter required information", Toast.LENGTH_SHORT).show();
                e.requestFocus();
                return false;
            }
        }
        return true;
    }
    public void publishAdvertisement()
    {
        Date c = Calendar.getInstance().getTime();
        mItem = new Item(nameOfItem.getText().toString(), ID, spinner.getSelectedItemPosition(), c,imageOfItem);
        mReference = FirebaseDatabase.getInstance().getReference().child("Advertisements").child(("ID number " + ID +""));
        Map newPost = new HashMap();
        newPost.put("Title", nameOfItem.getText().toString());
        newPost.put("Publisher ID",user.getUid());
        newPost.put("Category", spinner.getSelectedItem());
        newPost.put("Upload date", c);
        mReference.setValue( newPost);
        mReference = FirebaseDatabase.getInstance().getReference().child("Advertisements").child("Total number of advertisements");
        mReference.setValue(ID);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Photos/" + user.getUid() + "/" + nameOfItem.getText().toString() + ".jpg");
        mStorageRef.putFile(mUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        });


    }
    public void selectImage()
    {
        final String[] items = {"Camera", "Gallery","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add image of the item");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0)
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);
                }
                else if(which == 1)
                {
                    Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                }
                else if(which == 2)
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == Activity.RESULT_OK )
        {
            if ( requestCode == REQUEST_CAMERA)
            {
//                Bundle bundle = data.getExtras();
//                Bitmap bmp = (Bitmap) bundle.get("data");
//                imageOfItem.setImageBitmap(bmp);
            }
            else if ( requestCode == SELECT_FILE)
            {
                mUri = data.getData();
                imageOfItem.setImageURI(mUri);
            }
        }
    }


    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
