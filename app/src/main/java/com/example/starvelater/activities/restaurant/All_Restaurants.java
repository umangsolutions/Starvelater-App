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
import android.widget.Toast;

import com.example.starvelater.R;
import com.example.starvelater.SharedPref.MyAppPrefsManager;
import com.example.starvelater.activities.user.UserDashboard;
import com.example.starvelater.adapters.All_RestaurantsAdapter;
import com.example.starvelater.api.ApiInterface;
import com.example.starvelater.api.RetrofitClient;
import com.example.starvelater.jsonmodels.RestaurantsModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class All_Restaurants extends AppCompatActivity {

    List<String> restaurantName;
    List<String> restaurantLocation;
    List<Integer> restaurantImages;

    RecyclerView restaurantsList;
    MyAppPrefsManager myAppPrefsManager;

    All_RestaurantsAdapter restaurantsAdapter;

    ImageView backbutton;
    Toolbar restaurantToolBar;
    JsonObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__restaurants);

        restaurantsList = findViewById(R.id.restaurant_List);

        restaurantToolBar = findViewById(R.id.restaurantToolBar);
        setSupportActionBar(restaurantToolBar);
        myAppPrefsManager = new MyAppPrefsManager(All_Restaurants.this);

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

        if(myAppPrefsManager.getCity() == null){
            jsonObject = new JsonObject();
            jsonObject.addProperty("city","Kakinada");
            jsonObject.addProperty("area","Jayendra Nagar");

        }else {

            jsonObject = new JsonObject();
            jsonObject.addProperty("city", myAppPrefsManager.getCity());
            jsonObject.addProperty("area", myAppPrefsManager.getArea());
        }

            ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);

            apiInterface.processAllRestaurants(jsonObject).enqueue(new Callback<RestaurantsModel>() {
                @Override
                public void onResponse(Call<RestaurantsModel> call, Response<RestaurantsModel> response) {


                    if(response.isSuccessful()) {
                        RestaurantsModel restaurantsModel = response.body();
                        assert restaurantsModel!=null;

                        if(restaurantsModel.isStatus()) {

                            List<RestaurantsModel.DataBean> resultBeans = restaurantsModel.getData();

                            restaurantsList.setHasFixedSize(true);
                            restaurantsList.setLayoutManager(new LinearLayoutManager(All_Restaurants.this, LinearLayoutManager.HORIZONTAL, false));
                            restaurantsAdapter = new All_RestaurantsAdapter(All_Restaurants.this,resultBeans);
                            restaurantsList.setAdapter(restaurantsAdapter);

                            restaurantsAdapter.notifyDataSetChanged();


                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(All_Restaurants.this, LinearLayoutManager.VERTICAL, false);
                            restaurantsList.setLayoutManager(linearLayoutManager);
                            restaurantsList.setAdapter((RecyclerView.Adapter) restaurantsAdapter);

                        } else {
                            Toast.makeText(All_Restaurants.this, "Something is Wrong !", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(All_Restaurants.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RestaurantsModel> call, Throwable t) {
                    Toast.makeText(All_Restaurants.this, "Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            });
        }





    }
