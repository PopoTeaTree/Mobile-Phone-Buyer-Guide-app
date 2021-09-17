/**
 *  Mobile Collection
 *
 *  Keep mobile and manage mobile collection including get a mobile,
 *  add mobile, get a mobile by ID, get all mobile, and fetch images of mobile.
 *
 *  Created by
 *  Thitiporn Sukpartcharoen
 *
 *  16 September 2021
 */
package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MobileCollection {
    /**
     * Collection of mobiles
     */
    private ArrayList<Mobile> mobiles = new ArrayList<>();
    /**
     * Constructor instantiate array list of mobiles.
     */
    public MobileCollection()
    {
        this.mobiles = new ArrayList<>();
    }

    /**
     * Add mobile to mobile collection
     * @param mobile to add mobile
     */
    public void addMobile(Mobile mobile)
    {
        mobiles.add(mobile);
        fetchDataImage(mobile);
    }

    /**
     * Getter for all mobiles from collection
     * @return all mobiles
     */
    public ArrayList<Mobile> getAllMobiles()
    {
        return mobiles;
    }

    /**
     * Getter for mobile by mobile id
     * @return mobile with specified id or null if
     * not found
     */
    public Mobile getMobileByID(Integer id)
    {
        for(Mobile mobile:mobiles)
        {
            if(mobile.getId()==id)
            {
                return mobile;
            }
        }
        return null;
    }

    /**
     * fetch image url and collect in mobile object
     * @param  mobile
     */
    private void fetchDataImage(Mobile mobile){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://scb-test-mobile.herokuapp.com/")
                // add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // building our retrofit builder.
                .build();
        // create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<List<Image>> call = retrofitAPI.getMobileImage(mobile.getId());
        // receving response of medthod
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                try {
                    if(response.body() != null){
                        for (Image element : response.body()) {
                            Image image = element;
                            mobile.addImage(image);
                        }
                    }
                } catch (Exception e) {
                    // This will catch any exception, because they are all descended from Exception
                    System.out.println("Error " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                Log.i("Responsee", "Error found is : " + t.getMessage());
            }
        });
    }
}
