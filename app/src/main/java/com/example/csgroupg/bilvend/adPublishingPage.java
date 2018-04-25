package com.example.csgroupg.bilvend;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class adPublishingPage extends AppCompatActivity {
    private ImageButton publish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_publishing_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        publish = findViewById(R.id.publish);
        setSupportActionBar(toolbar);
        ExpandableLayout layout = findViewById(R.id.expandable_layout);
        layout.setRenderer(new ExpandableLayout.Renderer<Category,Advertisement>{
            @Override
            public void renderParent(View view, Category category, boolean isExpanded, int parentPosition) {
                view.findViewById(R.id.tv_parent_name).setText(category.name);
                view.findViewbById(R.id.arrow).setBackgroundResource(isExpanded?R.drawable.arrow_up:R.drawable.arrow_down);
            }

            @Override
            public void renderChild(View view, Advertisement ad, int parentPosition, int childPosition) {
                view.findViewById(R.id.tv_child_name).setText(Advertisement.name);
                            }
        });

        layout.addSection(getSection());
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishAdvertisement();
            }
        });

    }
    private Section<Category,Advertisement> getSection()
    {
        Section<Category,Advertisement>  section = new Section<>();
        List<Advertisement> advertisementList = new ArrayList<>();
        section.parent - Category;
        section.children.add(advertisementList);
        return section;
        }
    }

}
