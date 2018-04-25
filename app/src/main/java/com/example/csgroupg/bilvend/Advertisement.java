package com.example.csgroupg.bilvend;

import android.media.Image;
import java.util.Date;

public abstract class Advertisement {
    //properties
    private String name;
    private Date uploadDate;
    private String description;
    private Image advertisementImage;
    private int ID;
    private int categoryType;

    //constructor
    public Advertisement( String name, int ID, int categoryType, Date uploadDate) {
        this.name = name;
        this.ID = ID;
        this.categoryType = categoryType;
        this.uploadDate = uploadDate;
    }

    public Advertisement( String name) {
        this.name = name;
    }

    //methods
    public void setID( int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setName( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUploadDate(Date uploadDate) {
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

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setAdvertisementImage(Image advertisementImage) {
        this.advertisementImage = advertisementImage;
    }

    public Image getAdvertisementImage() {
        return advertisementImage;
    }
}