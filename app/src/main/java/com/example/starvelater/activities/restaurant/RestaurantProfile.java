package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starvelater.R;
import com.example.starvelater.adapters.RecycleGridAdapter;
import com.example.starvelater.adapters.RestaurantItemAdapter;
import com.example.starvelater.activities.user.UserDashboard;

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
    Toolbar restaurantToolBar;

    TextView txtRestaurantName,txtRestaurantLocation;

    Switch aSwitch;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

        datalist=findViewById(R.id.datalist);
        itemlist = findViewById(R.id.itemlist);

        linearLayout = findViewById(R.id.linearLayout);

        txtRestaurantName = findViewById(R.id.restaurant_name);
        txtRestaurantLocation = findViewById(R.id.restaurant_location);

        restaurantToolBar = findViewById(R.id.restaurantToolBar);
        setSupportActionBar(restaurantToolBar);

        aSwitch = findViewById(R.id.toggleswitch);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String restaurantName = bundle.getString("name");
        String restaurantLocation = bundle.getString("location");

        txtRestaurantName.setText(restaurantName);
        txtRestaurantLocation.setText(restaurantLocation);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(RestaurantProfile.this, "Show Vegetarian Items Only", Toast.LENGTH_SHORT).show();
                }
                if(!isChecked) {
                    Toast.makeText(RestaurantProfile.this, "Show All Items", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /*final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.restaurantAppBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Tandoori House");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });*/
        final ActionBar restaurantActionBar = getSupportActionBar();
        restaurantActionBar.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        restaurantActionBar.setDisplayShowTitleEnabled(false);

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