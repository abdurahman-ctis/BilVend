package com.example.csgroupg.bilvend;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class adPublishingPage extends AppCompatActivity {
    private ImageButton publish;
    private final String[] categories = {"Books"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_publishing_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        publish = findViewById(R.id.publish);
        setSupportActionBar(toolbar);
        ExpandableLayout layout = findViewById(R.id.expandable_layout);
        layout.setRenderer(new ExpandableLayout.Renderer() {
            @Override
            public void renderParent(View view, Object o, boolean b, int i) {

            }

            @Override
            public void renderChild(View view, Object o, int i, int i1) {

            }
        });

        layout.addSection(getSection());
//        publish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // publishAdvertisement();
//            }
//        });

    }

    private Section<Category, Advertisement> getSection() {
        Section<Category, Advertisement> section = new Section<>();
        // section.parent = Category;
        //section.children.add(categories);
        return section;
    }
}
