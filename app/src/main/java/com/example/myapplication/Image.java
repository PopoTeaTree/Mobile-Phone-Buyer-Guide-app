/**
 *  Image
 *
 *  Keep image detail and manage
 *
 *  Created by
 *  Thitiporn Sukpartcharoen
 *
 *  16 September 2021
 */
package com.example.myapplication;

import com.google.gson.annotations.Expose;

public class Image {
    @Expose
    int id;
    @Expose
    int mobile_id;
    @Expose
    String url;
    /**
     * Constructor instantiate image
     */
    public Image(String url,int id, int mobile_id){
        this.mobile_id = mobile_id;
        this.id = id;
        this.url = url;
    }
    /**
     * Getter image ID
     * return ID
     */
    public int getId() {
        return id;
    }
    /**
     * Getter URL of image
     * @return URL
     */
    public String getUrl() {
        return url;
    }
}
