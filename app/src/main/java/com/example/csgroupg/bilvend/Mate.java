package com.example.csgroupg.bilvend;

import java.util.Date;

import javax.xml.stream.Location;

/**
 * Created by talhasen on 25/04/2018.
 */

public class Mate extends Advertisement {

    int roommateNo;
    boolean dormOrFlat;
    String address;
    Location location;
    boolean type;

    public Item( double price, String name, int ID, int categoryType, Date uploadDate) {

        super( name, uploadDate, description, advertisementImage, ID, categoryType);

    }

    public setRoommateNo( int roommateNo) {
        this.roommateNo = roommateNo;
    }

    public getRoommateNo() {
        return roommateNo;
    }

    public setLocation( Location location) {
        this.location = location;
    }

    public getLocation() {
        return location;
    }

    public void setAdress( String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    /**
     *This method returns 0 if it is dorm, 1 if it is a flat!
     * @param type
     */
    public void setPropertyType( boolean type) {
        this.type = type;
    }

    public String getPropertyType() {
        if( !type )
            return "Dorm";
        else
            return "Flat";
    }



}
