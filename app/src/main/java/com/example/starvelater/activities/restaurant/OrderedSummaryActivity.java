package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.starvelater.R;
import com.example.starvelater.adapters.OrderedItemsAdapter;
import com.example.starvelater.model.NormalProducts;
import com.example.starvelater.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderedSummaryActivity extends AppCompatActivity {

    RecyclerView ordereditems;
    OrderedItemsAdapter orderedItemsAdapter;
    List<Product> recommendedArraylist;
    List<NormalProducts> normalArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_summary);
        ordereditems = findViewById(R.id.orderedItemList);

        recommendedArraylist = new ArrayList<>();
        normalArrayList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        recommendedArraylist = (List<Product>) bundle.getSerializable("recommended_items");
        normalArrayList = (List<NormalProducts>) bundle.getSerializable("normal_items");

        ordereditems = new RecyclerView(OrderedSummaryActivity.this, recommendedArraylist,normalArrayList);


    }
}