package com.example.csgroupg.bilvend;

import android.content.Intent;
import android.widget.Toast;

import com.example.csgroupg.bilvend.Advertisement;
import com.example.csgroupg.bilvend.AdvertisementContainer;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple user class for BilVend
 * @author Verdiyev Zulfugar
 * @version 26.04.2018
 */

public class User {

    //parameters
    private String name;
    private   String surname;
    private String email;
    private String number;    // PhoneNumber class should be created by ourselves....
    private String username;
    private AdvertisementContainer userAdvertisements;
    private AdvertisementContainer favoriteAdvertisements;
    private URL userImage;



    //constructors
    public User(String name, String surname, String mail, String number){
        this.name = name;
        this.surname = surname;
        email = mail;
        this.number = number;
        username = email.substring(0,email.indexOf('@'));  // takes username as a substring that comes before first occurrence of "@"
        userAdvertisements = null;
        userImage = null;
        favoriteAdvertisements = null;
    }

    //constructor for creating user without entering phone number
    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.number = null;
        username = email.substring(0,email.indexOf('@'));  // takes username as a substring that comes before first occurrence of "@"
        userAdvertisements = null;
        userImage = null;
        favoriteAdvertisements = null;
    }

    //methods
    public void setName( String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getMail(){
        return email;
    }

    public String getNumber(){
        return number;
    }

    public String getUsername()
    {
        return username;
    }

    public void setNumber(String num)
    {
        number = num;
    }

    public void setUserImage (URL newImage)
    {
        userImage = newImage;
    }

    public URL getUserImage()
    {
        return userImage;
    }

    public void addAdvertisment(Advertisement ad)
    {
        userAdvertisements.addAdvertisement(ad);
    }

    public void removeAdvertisement(Advertisement ad)
    {
        userAdvertisements.removeAdvertisement(ad);
    }

    public void addFavoriteAdvertisment(Advertisement ad) {
        favoriteAdvertisements.addAdvertisement(ad);}

    public void removeFavoriteAdvertisement(Advertisement ad) {
        favoriteAdvertisements.removeAdvertisement(ad);}
    public void saveToFirebase(FirebaseUser firebaseUser)
    {
        String current_user_id = firebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user_id);
        Map newPost = new HashMap();
        newPost.put("Name", this.getName());
        newPost.put("Surname", this.getSurname());
        newPost.put("Username", this.getUsername());
        databaseReference.setValue(newPost);
    }
    @Override
    public String toString() {
        return "name: " + name + "surname: " + surname + "mail: " + email + "number: " + number + "username: " + username;
    }
}