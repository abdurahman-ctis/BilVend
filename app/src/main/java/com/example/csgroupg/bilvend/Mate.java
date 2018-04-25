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

    public Mate( int roommateNo, boolean dormOrFlat, String address, Location location, String name, int ID, int categoryType, Date uploadDate) {

        super( name, ID, categoryType, uploadDate);
        setRoommateNo( roommateNo);
        setPropertyType( dormOrFlat);
        setAdress( address);
        setLocation( location);
    }

    public void setRoommateNo( int roommateNo) {
        this.roommateNo = roommateNo;
    }

    public int getRoommateNo() {
        return roommateNo;
    }

    public void setLocation( Location location) {
        this.location = location;
    }

    public Location getLocation() {
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
     * @param dormOrFlat
     */
    public void setPropertyType( boolean dormOrFlat) {
        this.dormOrFlat = dormOrFlat;
    }

    public String getPropertyType() {
        if( !dormOrFlat )
            return "Dorm";
        else
            return "Flat";
    }



}
