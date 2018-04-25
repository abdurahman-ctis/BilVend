package com.example.csgroupg.bilvend;

import android.media.Image;

import java.util.Date;

/**
 * Created by talhasen on 26/04/2018.
 */

public class Item extends Advertisement {

    double price;
    private String name;
    private Date uploadDate;
    private String description;
    private Image advertisementImage;
    private int ID;
    private int categoryType;

    public Item( price) {

        super( name, uploadDate, description, advertisementImage, ID, categoryType);
        setPrice( price);

    }

    public void setPrice( int price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }




}
