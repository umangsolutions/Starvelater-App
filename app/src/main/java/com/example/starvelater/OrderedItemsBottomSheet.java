package com.example.starvelater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.starvelater.adapters.RestaurantItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderedItemsBottomSheet extends AppCompatActivity {

    RecyclerView itemsList;
    List<String> titles;
    List<String> prices;
    RestaurantItemAdapter itemAdapter;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items_bottom_sheet);
        itemsList = findViewById(R.id.itemlist);
        linearLayout = findViewById(R.id.menuBottomSheet);
        titles = new ArrayList<>();
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

        itemAdapter = new RestaurantItemAdapter(this,titles,prices);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        itemsList.setLayoutManager(linearLayoutManager);
        itemsList.setAdapter((RecyclerView.Adapter) itemAdapter);
    }
}