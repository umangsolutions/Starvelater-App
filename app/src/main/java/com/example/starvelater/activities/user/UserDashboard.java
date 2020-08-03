package com.example.starvelater.activities.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starvelater.R;
import com.example.starvelater.SharedPref.MyAppPrefsManager;
import com.example.starvelater.activities.helpandsupport.Feedback;
import com.example.starvelater.activities.helpandsupport.HelpandSupportActivity;
import com.example.starvelater.activities.helpandsupport.TermsandConditionsActivity;
import com.example.starvelater.activities.restaurant.All_Restaurants;
import com.example.starvelater.activities.loginsignup.StartUpScreen;
import com.example.starvelater.adapters.userdashboard_adapters.MostPopularAdapter;
import com.example.starvelater.adapters.userdashboard_adapters.UserAllRestaurantsAdapter;
import com.example.starvelater.adapters.userdashboard_adapters.UtilityAdapter;
import com.example.starvelater.adapters.userdashboard_adapters.UtilityHelperClass;
import com.example.starvelater.api.ApiInterface;
import com.example.starvelater.api.RetrofitClient;
import com.example.starvelater.jsonmodels.LocationsModel;
import com.example.starvelater.jsonmodels.PopularRestaurantsModel;
import com.example.starvelater.jsonmodels.RestaurantsModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    RecyclerView mostPopularRecyclerView;
    RecyclerView allRestaurantsRecyclerView;
    RecyclerView utilityRecycler;
    RecyclerView.Adapter adapter;

    MyAppPrefsManager myAppPrefsManager;

    Spinner spinCity,spinArea;

    List<LocationsModel.DataBean> locationList;
    List<RestaurantsModel.DataBean> restaurantList;

    private GradientDrawable gradient1, gradient2, gradient3;

    private Dialog setLocationDialog;

    private LinearLayout setLocationLayout;

    TextView txtLocation;

    LinearLayout progressBar, dialogProgressBar;

    String TAG="RESPONSE_DATA";
    String city, area;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    ImageView qr_icon;
    TextView txtViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        setLocationLayout = findViewById(R.id.locationLayout);
        setLocationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogProgressBar.setVisibility(View.VISIBLE);
                openSetLocationDialog();
            }
        });

        txtLocation = findViewById(R.id.location);

        progressBar = findViewById(R.id.progressBar);
        dialogProgressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        myAppPrefsManager = new MyAppPrefsManager(UserDashboard.this);

        //Shared Preferences
        if(myAppPrefsManager.getUserName() != null) {
            Toast.makeText(this, "Welcome back " + myAppPrefsManager.getUserName(), Toast.LENGTH_SHORT).show();
        }


        if(myAppPrefsManager.getCity() == null){
            txtLocation.setText("Kakinada, Jayendra Nagar");
            city = "Kakinada";
            area = "Jayendra Nagar";
        }else{
            city = myAppPrefsManager.getCity();
            area = myAppPrefsManager.getArea();
            txtLocation.setText(city + ", " + area);

        }

        //Hooks
        mostPopularRecyclerView = findViewById(R.id.most_popular_recycler);
        allRestaurantsRecyclerView = findViewById(R.id.all_restaurants);
        utilityRecycler = findViewById(R.id.utility_recycler);

        txtViewAll = (TextView) findViewById(R.id.restaurantsViewAll);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        qr_icon = findViewById(R.id.qr_icon);
        qr_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(UserDashboard.this, "Sorry! Not Yet Implemented", Toast.LENGTH_SHORT).show();*/
                Intent intent2 = new Intent(UserDashboard.this, Qr_scan.class);
                startActivity(intent2);

            }
        });

        txtViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserDashboard.this, All_Restaurants.class);
                startActivity(intent1);
            }
        });

        navigationDrawer();
        utilityRecycler();
    }

    private void openSetLocationDialog() {
        setLocationDialog = new Dialog(UserDashboard.this);

        setLocationDialog.setContentView(R.layout.location_dialog);

        spinCity = setLocationDialog.findViewById(R.id.spinCity);
        spinArea = setLocationDialog.findViewById(R.id.spinArea);

        // Loading All Locations, calling Service
        ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);
        apiInterface.processLocationData().enqueue(new Callback<LocationsModel>() {
            @Override
            public void onResponse(Call<LocationsModel> call, Response<LocationsModel> response) {

                if(response.isSuccessful()) {
                    LocationsModel locationsModel = response.body();
                    assert locationsModel!=null;

                    if(locationsModel.isStatus()) {
                        locationList = locationsModel.getData();

                        ArrayList<String> cityList = new ArrayList<>();

                        // Creating City List From the Called Service
                        for(int i=0;i<locationList.size();i++) {
                            if(!cityList.contains(locationList.get(i).getCity_Name())) {
                                cityList.add(locationList.get(i).getCity_Name());
                            }
                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UserDashboard.this, R.layout.support_simple_spinner_dropdown_item, cityList);
                        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spinCity.setAdapter(arrayAdapter);

                        dialogProgressBar.setVisibility(View.GONE);


                    } else {
                        dialogProgressBar.setVisibility(View.GONE);
                        Toast.makeText(UserDashboard.this, "No Cities Found", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    dialogProgressBar.setVisibility(View.GONE);
                    Toast.makeText(UserDashboard.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LocationsModel> call, Throwable t) {
                dialogProgressBar.setVisibility(View.GONE);

                Toast.makeText(UserDashboard.this, "Please Try Again!", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<String> areaList = new ArrayList<>();

        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Extracting the Selected City
                String cityName = spinCity.getSelectedItem().toString();
                areaList.clear();
                //progressBar.setVisibility(View.VISIBLE);

                // Creating the Areas List based on city selected from the called Service
                for(int i=0;i<locationList.size();i++) {
                    if(locationList.get(i).getCity_Name().equals(cityName)){
                        areaList.add(locationList.get(i).getArea_Name());
                    }
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UserDashboard.this, R.layout.support_simple_spinner_dropdown_item, areaList);
                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinArea.setAdapter(arrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                progressBar.setVisibility(View.GONE);
            }
        });


        ImageView btnClose = setLocationDialog.findViewById(R.id.btnClose);

        Button btnSubmit = (Button) setLocationDialog.findViewById(R.id.btnSubmit);




          btnSubmit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String areaName = spinArea.getSelectedItem().toString();
                  String cityName = spinCity.getSelectedItem().toString();

                  if (!areaName.isEmpty() && !cityName.isEmpty()) {
                      // Initialising New Shared Preferences
                      txtLocation.setText(cityName + ", " + areaName);
                      myAppPrefsManager.setCity(cityName);
                      myAppPrefsManager.setArea(areaName);

                      allRestaurantsRecycler(cityName, areaName);

                      mostPopularRecycler(cityName, areaName);

                      Toast.makeText(UserDashboard.this, "Changed Location Successfully !", Toast.LENGTH_SHORT).show();
                      setLocationDialog.dismiss();
                  }
              }
          });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocationDialog.dismiss();
            }
        });


        Window window = setLocationDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        setLocationDialog.show();

    }

    private void mostPopularRecycler(String city, String area) {

        progressBar.setVisibility(View.GONE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("city",city);
        jsonObject.addProperty("area",area);

        ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);

        apiInterface.processAllRestaurants(jsonObject).enqueue(new Callback<RestaurantsModel>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantsModel> call, @NonNull Response<RestaurantsModel> response) {
                if(response.isSuccessful()) {

                    RestaurantsModel restaurantsModel = response.body();
                    assert restaurantsModel!=null;

                    if(restaurantsModel.isStatus()){
                        restaurantList = restaurantsModel.getData();
                        for(int i=0;i<restaurantList.size();i++) {
                            if(restaurantList.get(i).getType().equals("Most Popular")){

                            }else{

                            }
                        }

                        mostPopularRecyclerView.setHasFixedSize(true);
                        mostPopularRecyclerView.setLayoutManager(new LinearLayoutManager(UserDashboard.this, LinearLayoutManager.HORIZONTAL, false));

                        adapter = new MostPopularAdapter(UserDashboard.this,restaurantList);
                        mostPopularRecyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(UserDashboard.this, ""+restaurantsModel.getData(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantsModel> call, @NonNull Throwable t) {

            }
        });

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }


    private void allRestaurantsRecycler(String city, String area) {

        String type;

        progressBar.setVisibility(View.GONE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("city",city);
        jsonObject.addProperty("area",area);

        ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);

        apiInterface.processAllRestaurants(jsonObject).enqueue(new Callback<RestaurantsModel>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantsModel> call, @NonNull Response<RestaurantsModel> response) {


                if(response.isSuccessful()) {
                    RestaurantsModel restaurantsModel = response.body();
                    assert restaurantsModel!=null;

                    if(restaurantsModel.isStatus()) {

                        List<RestaurantsModel.DataBean> resultBeans = restaurantsModel.getData();
                        List<String> popularTitle = new ArrayList<>();
                        List<String> Description = new ArrayList<>();
                        List<String> Images = new ArrayList<>();
                        for(int i=0;i<resultBeans.size();i++){
                            if(resultBeans.get(i).getType().equals("Most Popular")){
                                popularTitle.add(resultBeans.get(i).getRestaurant_Name());
                                Description.add(resultBeans.get(i).getKnownFor());
                                Images.add(resultBeans.get(i).getRestaurantLogo());
                            }else{

                            }
                        }

                        allRestaurantsRecyclerView.setHasFixedSize(true);
                        allRestaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(UserDashboard.this, LinearLayoutManager.HORIZONTAL, false));
                        adapter = new UserAllRestaurantsAdapter(UserDashboard.this,resultBeans);
                        allRestaurantsRecyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(UserDashboard.this, "Something is Wrong !", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(UserDashboard.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantsModel> call, @NonNull Throwable t) {

                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(UserDashboard.this, "Please Try Again!", Toast.LENGTH_SHORT).show();
            }
        });



        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }


    //Navigation Drawer Functions
    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(UserDashboard.this);
        navigationView.setCheckedItem(R.id.nav_home);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home:
                Intent intent2 = new Intent(UserDashboard.this, UserDashboard.class);
                startActivity(intent2);
                break;
            case R.id.nav_all_categories:
                Intent intent = new Intent(UserDashboard.this, AllCategories.class);
                startActivity(intent);
                break;
            case R.id.nav_get_started:
                Intent intent1 = new Intent(UserDashboard.this, StartUpScreen.class);
                startActivity(intent1);
                break;
            case R.id.nav_terms_and_conditions:
                Intent intent3 = new Intent(UserDashboard.this, TermsandConditionsActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_support:
                Intent intent4= new Intent(UserDashboard.this, HelpandSupportActivity.class);
                startActivity(intent4);
                break;
            case R.id.feedback:
                Intent intent6 = new Intent(UserDashboard.this, Feedback.class);
                startActivity(intent6);
                break;

       }
        return true;
    }

    private void utilityRecycler() {

        progressBar.setVisibility(View.GONE);

        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});

        ArrayList<UtilityHelperClass> utility = new ArrayList<>();
        utility.add(new UtilityHelperClass("Restaurant", R.drawable.restaurants, gradient1));
        utility.add(new UtilityHelperClass("College Canteen", R.drawable.college, gradient2));
        utility.add(new UtilityHelperClass("Corporate Cafe", R.drawable.corporate, gradient3));

        utilityRecycler.setHasFixedSize(true);
        adapter = new UtilityAdapter(utility);
        utilityRecycler.setLayoutManager(new LinearLayoutManager(UserDashboard.this, LinearLayoutManager.HORIZONTAL, false));
        utilityRecycler.setAdapter(adapter);



    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

}
