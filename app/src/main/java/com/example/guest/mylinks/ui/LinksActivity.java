package com.example.guest.mylinks.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.mylinks.R;
import com.example.guest.mylinks.adapters.LinkListAdapter;
import com.example.guest.mylinks.models.Link;
import com.example.guest.mylinks.services.YelpService;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LinksActivity extends AppCompatActivity {
    public static final String TAG = LinksActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private LinkListAdapter mAdapter;

    public ArrayList<Link> mLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getLinks(location);
    }

    private void getLinks(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findLinks(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mLinks = yelpService.processResults(response);

                LinksActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new LinkListAdapter(getApplicationContext(), mLinks);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(LinksActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}

//public class LinksActivity extends AppCompatActivity {
//    public static final String TAG = LinksActivity.class.getSimpleName();
//
//    @Bind(R.id.locationTextView) TextView mLocationTextView;
//    @Bind(R.id.listView) ListView mListView;
//
//    public ArrayList<Link> mLinks = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_links);
//        ButterKnife.bind(this);
//
//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");
//
//        mLocationTextView.setText("Here are all the restaurants near: " + location);
//
//        getRestaurants(location);
//    }
//
//    private void getRestaurants(String location) {
//        final YelpService yelpService = new YelpService();
//
//        yelpService.findRestaurants(location, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                mLinks = yelpService.processResults(response);
//
//                LinksActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        String[] restaurantNames = new String[mLinks.size()];
//                        for (int i = 0; i < restaurantNames.length; i++) {
//                            restaurantNames[i] = mLinks.get(i).getName();
//                        }
//
//                        ArrayAdapter adapter = new ArrayAdapter(LinksActivity.this,
//                                android.R.layout.simple_list_item_1, restaurantNames);
//                        mListView.setAdapter(adapter);
//
//                        for (Link restaurant : mLinks) {
//                            Log.d(TAG, "Name: " + restaurant.getName());
//                            Log.d(TAG, "Phone: " + restaurant.getPhone());
//                            Log.d(TAG, "Website: " + restaurant.getWebsite());
//                            Log.d(TAG, "Image url: " + restaurant.getImageUrl());
//                            Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
//                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getAddress()));
//                            Log.d(TAG, "Categories: " + restaurant.getCategories().toString());
//                        }
//                    }
//                });
//            }
//        });
//    }
//}







//public class LinksActivity extends AppCompatActivity {
//    @Bind(R.id.locationTextView) TextView mLocationTextView;
//    @Bind(R.id.listView) ListView mListView;
//
//    public ArrayList<Link> mLinks = new ArrayList<>();
//
//    public static final String TAG = LinksActivity.class.getSimpleName();
//
//    private String[] restaurants = new String[] {"Sweet Hereafte", "Cricket", "Hawthorne Fish House", "Viking Soul Food",
//            "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell", "Me Kha Noodle Bar",
//            "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole" };
//
//    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fish Dishs", "Scandinavian", "Coffee",
//            "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar",
//            "Breakfast", "Mexican" };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_links);
//        ButterKnife.bind(this);
//
//        mListView = (ListView) findViewById(R.id.listView);
//        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
//
//        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1,
//               restaurants, cuisines); //must match constructor!
//
////        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);
//        mListView.setAdapter(adapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String restaurant = ((TextView)view).getText().toString();
//                Toast.makeText(LinksActivity.this, restaurant, Toast.LENGTH_LONG).show();
//                Log.v("TAG", "In the onItemClickListener!");
//            }
//        });
//
//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");
//        mLocationTextView.setText("Here are all the restaurants near: " + location);
//        Log.d("TAG", "In the onCreate method!");
//
//        getRestaurants(location);
//
//    }
//
//    private void getRestaurants(String location) {
//        final YelpService yelpService = new YelpService();
//        yelpService.findRestaurants(location, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    String jsonData = response.body().string();
//                    if (response.isSuccessful()) {
//                        Log.v(TAG, jsonData);
//                        mLinks = yelpService.processResults(response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//    }
//
//}
