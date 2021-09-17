/**
 *  Sort Price
 *
 *  This class is a sorting class to order mobile price by price and rating.
 *
 *  Created by
 *  Thitiporn Sukpartcharoen
 *
 *  16 September 2021
 */
package com.example.myapplication;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import kotlin.jvm.internal.FloatSpreadBuilder;

public class SortPrice {
    private HashMap<Integer,Double> hashMapPrice = null;
    private HashMap<Integer,Float> hashMapRating = null;
    /**
     * Constructor instantiate sort price for convert mobile collection
     * to hash map type.
     */
    public SortPrice(MobileCollection arr){
        this.hashMapPrice = changeToHashMapPrice(arr.getAllMobiles());
        this.hashMapRating = changeToHashMapRating(arr.getAllMobiles());

    }
    /**
     * Convert arraylist to hash map type with Price type
     * @param arr input arraylist
     * @return hash map which was converted from input arraylist
     */
    private HashMap<Integer,Double> changeToHashMapPrice(ArrayList<Mobile> arr){
        HashMap<Integer, Double> map = new HashMap<Integer, Double>();
        for (Mobile e: arr) {
            map.put(e.getId(),e.getPrice());
        }
        return map;
    }
    /**
     * Convert arraylist to hash map type with Rating type
     * @param arr input arraylist
     * @return hash map which was converted from input arraylist
     */
    private HashMap<Integer,Float> changeToHashMapRating(ArrayList<Mobile> arr){
        HashMap<Integer,Float> map = new HashMap<Integer,Float>();
        for (Mobile e: arr) {
            map.put(e.getId(),(Float) e.getRating());
        }
        return map;
    }
    /**
     * Sort price of mobiles by price from high to low
     * @return hash map which was sorted
     */
    public HashMap<Integer, Double> priceLowHigh(){
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Double> > list =
                new LinkedList<Map.Entry<Integer, Double> >(hashMapPrice.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double> >() {
            public int compare(Map.Entry<Integer, Double> o1,
                               Map.Entry<Integer, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    /**
     * Sort price of mobiles by price from low to high
     * @return hash map which was sorted
     */
    public HashMap<Integer, Double> priceHighLow(){
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Double> > list =
                new LinkedList<Map.Entry<Integer, Double> >(hashMapPrice.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double> >() {
            public int compare(Map.Entry<Integer, Double> o1,
                               Map.Entry<Integer, Double> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        // put data from sorted list to hashmap
        HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    /**
     * Sort price of mobiles by rating from high to low
     * @return hash map which was sorted
     */
    public HashMap<Integer, Float> priceRating(){
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Float> > list =
                new LinkedList<Map.Entry<Integer, Float> >(hashMapRating.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Float> >() {
            public int compare(Map.Entry<Integer, Float> o1,
                               Map.Entry<Integer, Float> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        // put data from sorted list to hashmap
        HashMap<Integer, Float> temp = new LinkedHashMap<Integer, Float>();
        for (Map.Entry<Integer, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
