package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.starvelater.R;
import com.example.starvelater.adapters.OrderedItemsAdapter;

public class OrderedSummaryActivity extends AppCompatActivity {

    RecyclerView ordereditems;
    OrderedItemsAdapter orderedItemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_summary);

        ordereditems = findViewById(R.id.orderedItemList);


    }
}