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
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.example.starvelater.jsonmodels.AreasModel;
import com.example.starvelater.jsonmodels.CitiesModel;
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
    private GradientDrawable gradient1, gradient2, gradient3;
    private Dialog setLocationDialog;
    private LinearLayout setLocationLayout;
    TextView txtLocation;
    LinearLayout progressBar, dialogProgressBar;

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
            mostPopularRecycler("Kakinada","Jayendra Nagar");
            allRestaurantsRecycler("Kakinada","Jayendra Nagar");
        }else{
            txtLocation.setText(myAppPrefsManager.getCity() + ", " + myAppPrefsManager.getArea());
            mostPopularRecycler(myAppPrefsManager.getCity(),myAppPrefsManager.getArea());
            allRestaurantsRecycler(myAppPrefsManager.getCity(),myAppPrefsManager.getArea());
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

        ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);

        // Loading Cities
        apiInterface.processDataCities().enqueue(new Callback<CitiesModel>() {
            @Override
            public void onResponse(Call<CitiesModel> call, Response<CitiesModel> response) {

                if(response.isSuccessful()) {
                    CitiesModel citiesModel = response.body();
                    assert citiesModel!=null;

                    if(citiesModel.isStatus()) {
                        List<CitiesModel.DataBean> resultBeans = citiesModel.getData();

                        ArrayList<String> cityList = new ArrayList<>();

                        for(int i=0;i<resultBeans.size();i++) {
                            cityList.add(resultBeans.get(i).getCity_Name());
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
            public void onFailure(Call<CitiesModel> call, Throwable t) {
                dialogProgressBar.setVisibility(View.GONE);

                Toast.makeText(UserDashboard.this, "Please Try Again!", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<String> areasList = new ArrayList<>();


        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cityName = spinCity.getSelectedItem().toString();

                //progressBar.setVisibility(View.VISIBLE);

                areasList.clear();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("city",cityName);

                apiInterface.processDataAreas(jsonObject).enqueue(new Callback<AreasModel>() {
                    @Override
                    public void onResponse(Call<AreasModel> call, Response<AreasModel> response) {
                        if(response.isSuccessful()) {
                            AreasModel areasModel = response.body();
                            assert areasModel !=null;

                            if(areasModel.isStatus()) {
                                List<AreasModel.DataBean> resultBeans = areasModel.getData();

                                for(int i=0;i<resultBeans.size();i++) {
                                    areasList.add(resultBeans.get(i).getArea_Name());
                                }

                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UserDashboard.this, R.layout.support_simple_spinner_dropdown_item, areasList);
                                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                                spinArea.setAdapter(arrayAdapter);

                              //  progressBar.setVisibility(View.GONE);


                            } else {
                                areasList.clear();

                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UserDashboard.this, R.layout.support_simple_spinner_dropdown_item, areasList);
                                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                                spinArea.setAdapter(arrayAdapter);

                               // progressBar.setVisibility(View.GONE);
                                Toast.makeText(UserDashboard.this, "No Areas found in Selected City" , Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<AreasModel> call, Throwable t) {
                        //progressBar.setVisibility(View.GONE);
                    }
                });

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
        jsonObject.addProperty("category","mp");

        ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);

        apiInterface.processAllPopular(jsonObject).enqueue(new Callback<PopularRestaurantsModel>() {
            @Override
            public void onResponse(Call<PopularRestaurantsModel> call, Response<PopularRestaurantsModel> response) {
                if(response.isSuccessful()) {

                    PopularRestaurantsModel popularRestaurantsModel = response.body();
                    assert popularRestaurantsModel!=null;

                    if(popularRestaurantsModel.isStatus()){
                        List<PopularRestaurantsModel.DataBean> dataBeanList = popularRestaurantsModel.getData();

                        mostPopularRecyclerView.setHasFixedSize(true);
                        mostPopularRecyclerView.setLayoutManager(new LinearLayoutManager(UserDashboard.this, LinearLayoutManager.HORIZONTAL, false));

                        adapter = new MostPopularAdapter(UserDashboard.this,dataBeanList);
                        mostPopularRecyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(UserDashboard.this, ""+popularRestaurantsModel.getData(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PopularRestaurantsModel> call, Throwable t) {

            }
        });

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }


    private void allRestaurantsRecycler(String city, String area) {

        progressBar.setVisibility(View.GONE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("city",city);
        jsonObject.addProperty("area",area);
        jsonObject.addProperty("type","Restaurant");

        ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);

        apiInterface.processAllRestaurants(jsonObject).enqueue(new Callback<RestaurantsModel>() {
            @Override
            public void onResponse(Call<RestaurantsModel> call, Response<RestaurantsModel> response) {


                if(response.isSuccessful()) {
                    RestaurantsModel restaurantsModel = response.body();
                    assert restaurantsModel!=null;

                    if(restaurantsModel.isStatus()) {

                        List<RestaurantsModel.DataBean> resultBeans = restaurantsModel.getData();

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
            public void onFailure(Call<RestaurantsModel> call, Throwable t) {
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
