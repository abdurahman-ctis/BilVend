package com.example.csgroupg.bilvend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;


public class adPublishingPage extends AppCompatActivity{

    private final String[] CATEGORIES = {"Categories", "Books", "Notes","Household","Electronic devices", "Roommate","Others"};

    private ImageButton publish;
    private FloatingActionButton camera;
    private ImageView imageOfItem;
    private EditText nameOfItem;
    private EditText priceOfItem;
    private EditText description;
    private View mDividerMode;
    private String title;
    private double price;
    private String contentInfo;
    private ArrayList<EditText> list;
    private Advertisement mItem;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_publishing_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
                            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),0);
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
               //    publishAdvertisement();
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
//    public void publishAdvertisement()
//    {
//        mItem = new Item(nameOfItem.getText().toString(), id, spinner.g
//        String name, int ID, int categoryType, Date uploadDate, ImageView advertisementImage
//    }

}
