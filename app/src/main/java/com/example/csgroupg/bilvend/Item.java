package com.example.csgroupg.bilvend;

import android.media.Image;

import java.util.Date;

/**
 * Created by talhasen on 26/04/2018.
 */

public class Item extends Advertisement {

    double price;

    public Item( double price, String name, int ID, int categoryType, Date uploadDate) {

        super( name, ID, categoryType, uploadDate,);
        setPrice( price);
    }

    public void setPrice( int price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }




}
