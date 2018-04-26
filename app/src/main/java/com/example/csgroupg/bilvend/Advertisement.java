package com.example.csgroupg.bilvend;

import android.media.Image;
import android.widget.ImageView;

import java.util.Date;

public abstract class Advertisement {
    //properties
    private String title;
    private Date uploadDate;
    private String description;
    private ImageView advertisementImage;
    private int ID;
    private int categoryType;

    //constructor
    public Advertisement(String name, int ID, int categoryType, Date uploadDate, ImageView advertisementImage) {
        this.title = name;
        this.ID = ID;
        this.categoryType = categoryType;
        this.uploadDate = uploadDate;
    }

    public Advertisement( String title) {
        this.title = title;
    }

    //methods
    public void setID( int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setTitle( String title) {
        this.title = this.title;
    }

    public String getTitle() {
        return title;
    }

    public void setUploadDate( Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setDescription( String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCategoryType( int categoryType) {
        this.categoryType = categoryType;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setAdvertisementImage( ImageView advertisementImage) {
        this.advertisementImage = advertisementImage;
    }

    public ImageView getAdvertisementImage() {
        return advertisementImage;
    }
}