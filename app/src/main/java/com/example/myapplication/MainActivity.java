/**
 *  Main Activity
 *
 *  This main activity for manage the application.
 *
 *  Created by
 *  Thitiporn Sukpartcharoen
 *
 *  16 September 2021
 */
package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mobile> mobileList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private MobileCollection mobiles;
    private SortPrice sortPrice;
    private ArrayList<Mobile> userFavorite = new ArrayList<>();
    private static final int DIALOG_LOADING = 1;
    /**
     * Create event
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobiles = new MobileCollection();
        new fetchSetView().execute();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Toolbar example");

        toolbar.setSubtitle("Android-er.blogspot.com");

    }
    /**
     * Get item option and call to sort mobile list
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        sortPrice = new SortPrice(mobiles);
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Sort_LowToHigh:
                Log.i("Responsee", ">>>>>>>Click Sort1: " + mobiles);
                HashMapToCollectionPrice(sortPrice.priceLowHigh());
                Log.i("Responsee", ">>>>>>>Sort1: " + mobiles);
                setRecycleView();
                return true;
            case R.id.Sort_HighToLow:
                Log.i("Responsee", ">>>>>>>Click Sort2: " + mobiles);
                HashMapToCollectionPrice(sortPrice.priceHighLow());
                Log.i("Responsee", ">>>>>>>Sort2: " + mobiles);
                setRecycleView();
                return true;
            case R.id.Sort_Rating:
                Log.i("Responsee", ">>>>>>>Click Sort3: " + mobiles);
                HashMapToCollectionRating(sortPrice.priceRating());
                Log.i("Responsee", ">>>>>>>Sort3: " + mobiles);
                setRecycleView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * fatch mobile data
     */
    private void fetchData(){
        // retrofit builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://scb-test-mobile.herokuapp.com/")
                // add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // building our retrofit builder.
                .build();
        // create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // calling a method to create a post and passing our modal class.
        Call<List<Mobile>> call = retrofitAPI.getMobileAll();

        // receving response of medthod
        call.enqueue(new Callback<List<Mobile>>() {
            @Override
            public void onResponse(Call<List<Mobile>> call, Response<List<Mobile>> response) {
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                try {
                    for (Mobile element : response.body()) {
                        Mobile newMobile = element;
                        mobiles.addMobile(newMobile);
                    }
                    Log.i("Responsee", "Mobiles: "+ mobiles.getAllMobiles());
                    setRecycleView();
                } catch (Exception e) {
                    // This will catch any exception, because they are all descended from Exception
                    System.out.println("Error " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Mobile>> call, Throwable t) {
                Log.i("Responsee", "Error found is : " + t.getMessage());
            }
        });
    }
    /**
     * Set item card view of mobile
     */
    private void setRecycleView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        adapter = new MainAdapter();
        adapter.setItemList(createItem());
        recyclerView.setAdapter(adapter);
    }

    /**
     * Set mobile detial and image to card in item view
     * @return  item of card
     */
    private List<BaseItem> createItem() {
        List<BaseItem> itemList = new ArrayList<>();
        for (Mobile element : mobiles.getAllMobiles()) {
//            String urlString = element.getImage().get(0).getUrl();
//            Log.i("Responsee", "URL image UNI: " + element.getImage());
            String urlString ="https://www.hihonor.com/content/dam/honor/global/blog/2018/honor-10-lite-another-budget-gaming-phone-in-2018/blog-img1-honor-10-lite-another-budget-gaming-phone-in-2018-1-3715.jpg";
            itemList.add(new CardViewItem().setImage(urlString));
            itemList.add(new CardViewItem().setText(element.getName()+"\n"+element.getPrice().toString()));
            Log.i("Responsee", "Element : " + element);
        }
        Log.i("Responsee", "itemList : " + itemList);
        return itemList;
    }
    /**
     * Sort mobile collection by price
     * @param hashMap_buffer    unsort hash map
     * @return  MobileCollection sorted mobile collection
     */
    private MobileCollection HashMapToCollectionPrice(HashMap<Integer, Double> hashMap_buffer){
        MobileCollection mobiles_buffer = new MobileCollection();
        for (Map.Entry me : hashMap_buffer.entrySet()) {
            Mobile mobile = mobiles.getMobileByID((int) me.getKey());
            mobiles_buffer.addMobile(mobile);
        }
        return mobiles_buffer;
    }
    /**
     * Sort mobile collection by rating
     * @param hashMap_buffer    unsort hash map
     * @return  MobileCollection sorted mobile collection
     */
    private MobileCollection HashMapToCollectionRating(HashMap<Integer, Float> hashMap_buffer){
        MobileCollection mobiles_buffer = new MobileCollection();
        for (Map.Entry me : hashMap_buffer.entrySet()) {
            Mobile mobile = mobiles.getMobileByID((int) me.getKey());
            mobiles_buffer.addMobile(mobile);
        }
        return mobiles_buffer;
    }
    /**
     * subclass od fetch data and set view
     */
    private class fetchSetView extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            fetchData();
            return null;
        }
        protected void onProgressUpdate(Integer... values) {
            setRecycleView();
        }
        // A callback method executed on UI thread on starting the task
        @Override
        protected void onPreExecute() {
            // Getting reference to the TextView tv_counter of the layout activity_main
            createItem();
        }
    }
    /**
     * Popup menu of sort and call function to process mobile list
     * @param v    view of popup menu
     */
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                try {
                    sortPrice = new SortPrice(mobiles);
                    // Handle item selection
                    switch (item.getItemId()) {
                        case R.id.Sort_LowToHigh:
                            HashMapToCollectionPrice(sortPrice.priceLowHigh());
                            return true;
                        case R.id.Sort_HighToLow:
                            HashMapToCollectionPrice(sortPrice.priceHighLow());
                            return true;
                        case R.id.Sort_Rating:
                            HashMapToCollectionRating(sortPrice.priceRating());
                            return true;
                        default:
                            return false;
                    }
                } catch (Exception e) {
                    // This will catch any exception, because they are all descended from Exception
                    System.out.println("Error " + e.getMessage());
                }
                return false;
            }
        });
        // Inflate popup menu
        popup.inflate(R.menu.menu_main);
        popup.show();
    }
}