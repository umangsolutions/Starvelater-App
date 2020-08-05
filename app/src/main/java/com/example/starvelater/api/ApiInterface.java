package com.example.starvelater.api;

import com.example.starvelater.jsonmodels.CategoryItemsModel;
import com.example.starvelater.jsonmodels.LocationsModel;
import com.example.starvelater.jsonmodels.RestaurantsModel;
import com.example.starvelater.jsonmodels.UserLoginModel;
import com.example.starvelater.jsonmodels.UserRegistrationModel;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("load_cities_areas.php")
    Call<LocationsModel> processLocationData();

    @Headers("Content-Type: application/json")
    @POST("load_restaurants.php")
    Call<RestaurantsModel> processRestaurants(@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("load_all_items.php")
    Call<CategoryItemsModel> processAllItems(@Body JsonObject object);



/*    @Headers("Content-Type: application/json")
    @POST("load_areas.php")
    Call<AreasModel> processDataAreas(@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("load_most_popular.php")
    Call<PopularRestaurantsModel> processAllPopular(@Body JsonObject object);*/

    @Headers("Content-Type: application/json")
    @POST("load_utility_categories.php")
    Call<RestaurantsModel> processAllRestaurants(@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("load_utility_categories.php")
    Call<RestaurantsModel> processAllCollegeCanteens(@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("load_utility_categories.php")
    Call<RestaurantsModel> processAllCorporateCafes(@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("user_registration.php")
    Call<UserRegistrationModel> processUserRegistration(@Body JsonObject object);

    @Headers("Content-Type: application/json")
    @POST("user_login.php")
    Call<UserLoginModel> processUserLogin(@Body JsonObject object);


/*

    @Headers("Content-Type: application/json")
    @GET("allusers.php")
    Call<UserDetails> processDataAllUsers();*/
}
