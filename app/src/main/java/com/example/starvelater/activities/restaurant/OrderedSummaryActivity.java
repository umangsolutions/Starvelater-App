package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starvelater.R;
import com.example.starvelater.activities.user.UserDashboard;
import com.example.starvelater.adapters.OrderedItemsAdapter;
import com.example.starvelater.model.NormalProducts;
import com.example.starvelater.model.Product;

import java.io.Serializable;
import java.text.DecimalFormat;
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
    TextView restaurantName,restaurantAddress, totalItemCount,totalPrice;

    Button btnPreOrder,btnTakeAway,btnDineIn,btnCheckOut;
    ImageView imgDisplay;
    String selectedBtn = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_summary);
        ordereditems = findViewById(R.id.orderedItemList);

        backbutton = findViewById(R.id.back_button);

        btnPreOrder = findViewById(R.id.preorder);
        btnTakeAway = findViewById(R.id.takeaway);
        btnDineIn = findViewById(R.id.dinein);

        imgDisplay = findViewById(R.id.display_image);

        btnCheckOut = findViewById(R.id.checkout);


        btnPreOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBtn = "PreOrder";

                // setting Image on the Right Side
                imgDisplay.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_preorder));

                // Highlighting the Button
                btnPreOrder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnPreOrder.setTextColor(Color.WHITE);

                //setting other Buttons Background as White Color
                btnTakeAway.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnTakeAway.setBackgroundColor(Color.WHITE);
                btnTakeAway.setBackground(getResources().getDrawable(R.drawable.terms_border));


                btnDineIn.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnDineIn.setBackgroundColor(Color.WHITE);
                btnDineIn.setBackground(getResources().getDrawable(R.drawable.terms_border));

            }
        });

        btnDineIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBtn = "DineIn";

                // setting Image on the Right Side
                imgDisplay.setImageDrawable(getResources().getDrawable(R.drawable.ic_food_delivery_dinein));

                // Highlighting the Button
                btnDineIn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnDineIn.setTextColor(Color.WHITE);

                //setting other Buttons Background as White Color
                btnTakeAway.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnTakeAway.setBackgroundColor(Color.WHITE);
                btnTakeAway.setBackground(getResources().getDrawable(R.drawable.terms_border));

                btnPreOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnPreOrder.setBackgroundColor(Color.WHITE);
                btnPreOrder.setBackground(getResources().getDrawable(R.drawable.terms_border));

            }
        });

        btnTakeAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBtn = "TakeAway";

                // setting Image on the Right Side
                imgDisplay.setImageDrawable(getResources().getDrawable(R.drawable.ic_lunch_takeaway));

                // Highlighting the Button
                btnTakeAway.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnTakeAway.setTextColor(Color.WHITE);

                //setting other Buttons Background as White Color
                btnDineIn.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnDineIn.setBackgroundColor(Color.WHITE);
                btnDineIn.setBackground(getResources().getDrawable(R.drawable.terms_border));

                btnPreOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnPreOrder.setBackgroundColor(Color.WHITE);
                btnPreOrder.setBackground(getResources().getDrawable(R.drawable.terms_border));

            }
        });

        recommendedArraylist = new ArrayList<>();
        normalArrayList = new ArrayList<>();

        finalArrayList = new ArrayList<>();


        restaurantName = findViewById(R.id.restaurant_name);
        restaurantAddress = findViewById(R.id.restAddress);
        totalItemCount = findViewById(R.id.totalQuantity);
        totalPrice = findViewById(R.id.totalPrice);

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

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String formattedValue = decimalFormat.format(grandtotal);

        totalPrice.setText("₹" + formattedValue);




        orderedItemsAdapter = new OrderedItemsAdapter(OrderedSummaryActivity.this,finalArrayList);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderedSummaryActivity.this,LinearLayoutManager.VERTICAL,false);
        ordereditems.setLayoutManager(linearLayoutManager);
        ordereditems.setAdapter(orderedItemsAdapter);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderedSummaryActivity.this, "Order Placed Successfully with mode of " + selectedBtn, Toast.LENGTH_SHORT).show();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderedSummaryActivity.this, RestaurantProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", restName);
                bundle.putString("location",location);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }
}