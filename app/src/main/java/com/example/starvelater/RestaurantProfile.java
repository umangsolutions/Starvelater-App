package com.example.starvelater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.starvelater.helperClasses.RecycleGridAdapter;
import com.example.starvelater.helperClasses.RestaurantItemAdapter;
import com.example.starvelater.user.UserDashboard;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class RestaurantProfile extends AppCompatActivity {
    RecyclerView datalist;
    RecyclerView itemlist;
    List<String> titles;
    List<Integer> images;
    List<String> prices;
    RecycleGridAdapter  adapter;
    RestaurantItemAdapter itemAdapter;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        datalist=findViewById(R.id.datalist);
        itemlist = findViewById(R.id.itemlist);

        backbutton = (ImageView) findViewById(R.id.back_button);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantProfile.this, UserDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        titles = new ArrayList<>();
        images = new ArrayList<>();
        prices = new ArrayList<>();

        titles.add("Beverages");
        titles.add("Biriyani north india");
        titles.add("red cherry");
        titles.add("Italian Fast Food");
        titles.add("Amarican Italian Food");
        titles.add("Beverages");

        prices.add("₹ 500");
        prices.add("₹ 1500");
        prices.add("₹ 250");
        prices.add("₹ 369");
        prices.add("₹ 400");
        prices.add("₹ 128");


       /*titles.add("Beverages");
        titles.add("Biriyani north india");
        titles.add("red cherry");
        titles.add("Italian Fast Food");
        titles.add("Amarican Italian Food");
        titles.add("Biriyani north india");*/


        images.add(R.drawable.photo6);
        images.add(R.drawable.photo7);
        images.add(R.drawable.photo8);
        images.add(R.drawable.photo9);
        images.add(R.drawable.photo10);
        images.add(R.drawable.photo11);

        /*images.add(R.drawable.photo6);
        images.add(R.drawable.photo7);
        images.add(R.drawable.photo8);
        images.add(R.drawable.photo9);
        images.add(R.drawable.photo10);
        images.add(R.drawable.photo11);*/


         adapter = new RecycleGridAdapter(this,titles,images);

         itemAdapter = new RestaurantItemAdapter(this,titles,prices);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        itemlist.setLayoutManager(linearLayoutManager);
        itemlist.setAdapter((RecyclerView.Adapter) itemAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        datalist.setLayoutManager(gridLayoutManager);
        datalist.setAdapter((RecyclerView.Adapter) adapter);
    }
}