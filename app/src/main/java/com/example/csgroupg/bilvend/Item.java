package com.example.csgroupg.bilvend;

import android.media.Image;
import android.support.annotation.StringDef;
import android.widget.ImageView;

import java.util.Date;

/**
 * Created by talhasen on 26/04/2018.
 */

public class Item extends Advertisement {

    double price;
    private String description;
    private ImageView advertisementImage;

    public Item(String name, int ID, int categoryType, Date uploadDate, ImageView advertisementImage) {

        super( name, ID, categoryType, uploadDate, advertisementImage);
        setPrice(price);

    }

    public void setPrice( double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }




}
