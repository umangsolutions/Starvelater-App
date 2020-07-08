package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.starvelater.R;
import com.example.starvelater.adapters.OrderedItemsAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class BottomSheet extends AppCompatActivity {

    RecyclerView itemsList;
    List<String> titles;
    List<String> prices;
    OrderedItemsAdapter itemAdapter;
    private CardView cardView;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        itemsList = findViewById(R.id.ordereditems);

        /*cardView = (CardView) findViewById(R.id.ordered_menu_dialog);*/

        bottomSheetBehavior = BottomSheetBehavior.from(cardView);



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

        /*itemAdapter = new OrderedItemsAdapter(bottomSheetBehavior,titles,prices);*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getParent(),LinearLayoutManager.VERTICAL,false);
        itemsList.setLayoutManager(linearLayoutManager);
        itemsList.setAdapter((RecyclerView.Adapter) itemAdapter);
    }
}