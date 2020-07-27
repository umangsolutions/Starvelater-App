package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.starvelater.R;
import com.example.starvelater.activities.user.UserDashboard;
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
    List<Product> finalArrayList;
    ImageView backbutton;
    int grandtotal = 0,totalcount=0;
    TextView restaurantName,restaurantAddress, totalItemCount,finalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_summary);
        ordereditems = findViewById(R.id.orderedItemList);

        backbutton = findViewById(R.id.back_button);

        recommendedArraylist = new ArrayList<>();
        normalArrayList = new ArrayList<>();

        finalArrayList = new ArrayList<>();


        restaurantName = findViewById(R.id.restaurant_name);
        restaurantAddress = findViewById(R.id.restAddress);
        totalItemCount = findViewById(R.id.totalQuantity);

        ordereditems = findViewById(R.id.orderedItemList);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String restName = bundle.getString("restaurantName");
        String location = bundle.getString("restaurantAddress");

        restaurantName.setText(restName);
        restaurantAddress.setText(location);


        recommendedArraylist = (List<Product>) getIntent().getSerializableExtra("recommendedItemsList");
        normalArrayList = (List<Product>) getIntent().getSerializableExtra("normal_itemsList");



        recommendedArraylist.addAll(normalArrayList);

        /*for(int i=0;i<recommendedArraylist.size();i++) {
            if(recommendedArraylist.get(i).getQuantity() == 0) {
                recommendedArraylist.remove(i);
            }
        }*/


        for(int i=0;i<recommendedArraylist.size();i++){
            if(recommendedArraylist.get(i).getQuantity() !=0) {
                finalArrayList.add(recommendedArraylist.get(i));
                grandtotal += recommendedArraylist.get(i).getItemTotalPrice();
                totalcount +=recommendedArraylist.get(i).getQuantity();
            }
        }

        totalItemCount.setText( totalcount+ " Items");




        orderedItemsAdapter = new OrderedItemsAdapter(OrderedSummaryActivity.this,finalArrayList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderedSummaryActivity.this,LinearLayoutManager.VERTICAL,false);
        ordereditems.setLayoutManager(linearLayoutManager);
        ordereditems.setAdapter(orderedItemsAdapter);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderedSummaryActivity.this, UserDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}