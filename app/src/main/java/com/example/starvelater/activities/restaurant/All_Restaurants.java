package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.starvelater.R;
import com.example.starvelater.activities.user.UserDashboard;
import com.example.starvelater.adapters.All_RestaurantsAdapter;

import java.util.ArrayList;
import java.util.List;

public class All_Restaurants extends AppCompatActivity {

    List<String> restaurantName;
    List<String> restaurantLocation;
    List<Integer> restaurantImages;

    RecyclerView restaurantsList;

    All_RestaurantsAdapter restaurantsAdapter;

    ImageView backbutton;
    Toolbar restaurantToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__restaurants);

        restaurantsList = findViewById(R.id.restaurant_List);

        restaurantToolBar = findViewById(R.id.restaurantToolBar);
        setSupportActionBar(restaurantToolBar);

        final ActionBar restaurantActionBar = getSupportActionBar();
        restaurantActionBar.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        restaurantActionBar.setDisplayShowTitleEnabled(false);

        backbutton = (ImageView) findViewById(R.id.back_button);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(All_Restaurants.this, UserDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        restaurantName = new ArrayList<>();
        restaurantImages = new ArrayList<>();
        restaurantLocation = new ArrayList<>();

        restaurantName.add("Barbeque Nation");
        restaurantName.add("Chutneys");
        restaurantName.add("Paradise");
        restaurantName.add("Paaka");
        restaurantName.add("Taj Restaurant");
        restaurantName.add("Yati Restaurant");

        restaurantLocation.add("JayendraNagar,Kakinada");
        restaurantLocation.add("Gachibowli, Hyderabad");
        restaurantLocation.add("Bhilai, Chattisgarh");
        restaurantLocation.add("Chebrolu, West Godavari");
        restaurantLocation.add("Rajahmundry, East Godavari");
        restaurantLocation.add("Chennai, TamilNadu");

        restaurantImages.add(R.drawable.bbqnation);
        restaurantImages.add(R.drawable.chutneys);
        restaurantImages.add(R.drawable.paradise);
        restaurantImages.add(R.drawable.paaka);
        restaurantImages.add(R.drawable.taj);
        restaurantImages.add(R.drawable.photo11);

        restaurantsAdapter = new All_RestaurantsAdapter(this, restaurantName, restaurantLocation, restaurantImages);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        restaurantsList.setLayoutManager(linearLayoutManager);
        restaurantsList.setAdapter((RecyclerView.Adapter) restaurantsAdapter);


    }
}