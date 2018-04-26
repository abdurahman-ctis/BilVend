package com.example.csgroupg.bilvend;

import java.util.ArrayList;

/**
 * Created by lenovo on 25.04.2018.
 */

public class AdvertisementContainer
{
    //properties
    private ArrayList<Advertisement> container;
    private ArrayList<Advertisement> randomContainer;

    //constructor
    public AdvertisementContainer(){

        container = new ArrayList<Advertisement>();
        randomContainer = new ArrayList<Advertisement>();
    }

    //methods

    public void addAdvertisement( Advertisement ad){

        container.add(ad);
    }

    public void removeAdvertisement(Advertisement ad){

        for(int i = 0 ; i < container.size() ; i++){

            if(ad == container.get(i))container.remove(i);
        }
    }

    public Advertisement getAdvertisement(int ID){

        for(int i = 0 ; i < container.size() ; i++){

            if(ID == container.get(i).getID())
                return container.get(i);
        }
        return null;
    }

    public void removeAdvertisment(int ID){

        for(int i = 0 ; i < container.size() ; i++){

            if(ID == container.get(i).getID())container.remove(i);
        }

    }

    public Advertisement getRandomAdvertisement(){

        int randomNumber;

        randomNumber = (int)(Math.random() * container.size() + 1);

        for(int i = 0 ; i < randomContainer.size() ; i++){

            if(randomContainer.get(i) == container.get(randomNumber))return getRandomAdvertisement();
        }
        randomContainer.add(container.get(randomNumber));
        return container.get(randomNumber);
    }

    public ArrayList getAdvertisements(String word){

        int relativeness;
        ArrayList<Advertisement> relativeAds;


        relativeness = 0;
        relativeAds = new ArrayList<Advertisement>();

        for(int i = 0 ; i < container.size() ; i++){

            if(word == container.get(i).getTitle())relativeness = relativeness + 10;
            if(word == container.get(i).getDescription())relativeness = relativeness + 5;

            if(relativeness < 10)relativeAds.add(container.get(i));
            if(relativeness >= 10)relativeAds.set(0, container.get(i));
            relativeness = 0;
        }

        return relativeAds;

    }

    public ArrayList getItemsOfCategory(int type){

        ArrayList<Advertisement> categoryAds;

        categoryAds = new ArrayList<Advertisement>();

        for(int i = 0 ; i < container.size() ; i++){

            if(container.get(i).getCategoryType() == type)categoryAds.add(container.get(i));
        }
        return categoryAds;
    }

}
