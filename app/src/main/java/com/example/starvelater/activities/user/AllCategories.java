package com.example.starvelater.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.starvelater.R;
import com.example.starvelater.activities.college_canteen.All_CollegeCanteen;
import com.example.starvelater.activities.corporate_cafe.All_CorporateCafe;
import com.example.starvelater.activities.restaurant.All_Restaurants;

public class AllCategories extends AppCompatActivity {

    ImageView backButton;
    RelativeLayout restaurants,canteens,corporatecafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_categories);

        //Hooks
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllCategories.super.onBackPressed();

            }
        });

        restaurants = findViewById(R.id.restaurants);
        canteens=findViewById(R.id.canteens);
        corporatecafe=findViewById(R.id.corporatecafe);

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllCategories.this, All_Restaurants.class);
                startActivity(intent);
            }
        });

        canteens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllCategories.this, All_CollegeCanteen.class);
                startActivity(intent);
            }
        });

        corporatecafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllCategories.this, All_CorporateCafe.class);
                startActivity(intent);
            }
        });


    }
}
