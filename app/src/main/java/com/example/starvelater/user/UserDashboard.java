package com.example.starvelater.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.starvelater.R;
import com.example.starvelater.helperClasses.homeAdapter.FeaturedAdapter;
import com.example.starvelater.helperClasses.homeAdapter.FeaturedHelperClass;
import com.example.starvelater.helperClasses.homeAdapter.PopularAdapter;
import com.example.starvelater.helperClasses.homeAdapter.PopularHelperClass;
import com.example.starvelater.helperClasses.homeAdapter.UtilityAdapter;
import com.example.starvelater.helperClasses.homeAdapter.UtilityHelperClass;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    RecyclerView featuredRecycler;
    RecyclerView popularRecycler;
    RecyclerView utilityRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        popularRecycler = findViewById(R.id.popular_recycler);
        utilityRecycler = findViewById(R.id.utility_recycler);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        navigationDrawer();

        featuredRecycler();
        popularRecycler();
        utilityRecycler();
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

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
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
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    private void utilityRecycler() {

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

    private void popularRecycler() {

        popularRecycler.setHasFixedSize(true);
        popularRecycler.setLayoutManager(new LinearLayoutManager(UserDashboard.this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<PopularHelperClass> popularRestaurants = new ArrayList<>();
        popularRestaurants.add(new PopularHelperClass(R.drawable.paradise, "Paradise", "Known For:\n" + "Legendary Biryani"));
        popularRestaurants.add(new PopularHelperClass(R.drawable.swathi, "New Swathi", "Known For:\n" + "North-Indian Cuisine\n" + "South-Indian Cuisine"));
        popularRestaurants.add(new PopularHelperClass(R.drawable.paaka, "Paaka", "Known For:\n" + "Organic Cafe\n" + "Cultural Space"));


        adapter = new PopularAdapter(popularRestaurants);
        popularRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(UserDashboard.this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelperClass> featuredRestaurants = new ArrayList<>();
        featuredRestaurants.add(new FeaturedHelperClass(R.drawable.chutneys, "Chutneys", "Known For:\n" + "Authentic South Indian Food Well Sanitized Kitchen"));
        featuredRestaurants.add(new FeaturedHelperClass(R.drawable.bbqnation, "Barbeque Nation", "Known For:\n" + "Indoor Barbeque concept 'Do-It-Yourself' grilling"));
        featuredRestaurants.add(new FeaturedHelperClass(R.drawable.taj, "Taj Krishna", "Known For:\n" + "Regal Ambiance Reminiscent Of The Days Of The Nizams"));


        adapter = new FeaturedAdapter(featuredRestaurants);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }

}
