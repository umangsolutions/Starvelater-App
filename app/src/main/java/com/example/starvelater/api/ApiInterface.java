package com.example.starvelater.api;

import com.example.starvelater.jsonmodels.AreasModel;
import com.example.starvelater.jsonmodels.CitiesModel;
import com.example.starvelater.jsonmodels.PopularRestaurantsModel;
import com.example.starvelater.jsonmodels.RestaurantsModel;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {


    @Headers("Content-Type: application/json")
    @POST("load_cities.php")
    Call<CitiesModel> processDataCities();

    @Headers("Content-Type: application/json")
    @POST("load_areas.php")
    Call<AreasModel> processDataAreas(@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("load_restaurants.php")
    Call<RestaurantsModel> processAllRestaurants(@Body JsonObject object);


    @Headers("Content-Type: application/json")
    @POST("load_restaurants.php")
    Call<PopularRestaurantsModel> processAllPopular(@Body JsonObject object);


/*

    @Headers("Content-Type: application/json")
    @GET("allusers.php")
    Call<UserDetails> processDataAllUsers();*/
}
