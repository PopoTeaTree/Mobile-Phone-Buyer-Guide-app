/**
 *  Mobile
 *
 *  Keep mobile detail and manage mobile detail
 *
 *  Created by
 *  Thitiporn Sukpartcharoen
 *
 *  16 September 2021
 */
package com.example.myapplication;

import android.util.Log;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Mobile {
    @Expose
    int id;
    @Expose
    float rating;
    @Expose
    String description;
    @Expose
    String name;
    @Expose
    Double price;
    @Expose
    ArrayList<Image> imageCollection = new ArrayList<>();
    /**
     * Constructor instantiate mobile with init mobile detail.
     */
    public Mobile(String band,int id, String description, String name, Double price){
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
    }
    /**
     * Getter for mobile ID
     * @return TD
     */
    public int getId() {
        return id;
    }
    /**
     * Getter for mobile rating
     * @return rating
     */
    public float getRating() {
        return rating;
    }
    /**
     * Getter for mobile description
     * @return description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Getter for mobile name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Getter for mobile price
     * @return name
     */
    public Double getPrice() {
        return price;
    }
    /**
     * add image to image list of mobile
     * @param image mobile image
     */
    public void addImage(Image image) {
        imageCollection.add(image);
    }
    /**
     * add image to image list of mobile
     * @return arraylist of image
     */
    public ArrayList<Image> getImage(){
        return imageCollection;
    }
}
