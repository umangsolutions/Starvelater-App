package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.starvelater.R;
import com.example.starvelater.adapters.OrderedItemsAdapter;
import com.example.starvelater.model.NormalProducts;
import com.example.starvelater.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderedSummaryActivity extends AppCompatActivity implements Serializable {

    RecyclerView ordereditems;
    OrderedItemsAdapter orderedItemsAdapter;
    List<Product> recommendedArraylist;
    List<Product> normalArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_summary);
        ordereditems = findViewById(R.id.orderedItemList);

        recommendedArraylist = new ArrayList<>();
        normalArrayList = new ArrayList<>();

        ordereditems = findViewById(R.id.orderedItemList);

        /*Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        */
        recommendedArraylist = (List<Product>) getIntent().getSerializableExtra("recommendedItemsList");
        normalArrayList = (List<Product>) getIntent().getSerializableExtra("normal_itemsList");


        recommendedArraylist.addAll(normalArrayList);

        /*for(int i=0;i<recommendedArraylist.size();i++) {
            if(recommendedArraylist.get(i).getQuantity() == 0) {
                recommendedArraylist.remove(i);
            }
        }*/
        orderedItemsAdapter = new OrderedItemsAdapter(OrderedSummaryActivity.this,recommendedArraylist);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderedSummaryActivity.this,LinearLayoutManager.VERTICAL,false);
        ordereditems.setLayoutManager(linearLayoutManager);
        ordereditems.setAdapter(orderedItemsAdapter);




    }
}