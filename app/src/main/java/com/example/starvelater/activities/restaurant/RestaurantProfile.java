package com.example.starvelater.activities.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starvelater.R;
import com.example.starvelater.adapters.OrderedItemsAdapter;
import com.example.starvelater.adapters.RecycleGridAdapter;
import com.example.starvelater.adapters.RestaurantItemAdapter;
import com.example.starvelater.activities.user.UserDashboard;
import com.example.starvelater.control.BottomSheetBehaviorRecyclerManager;
import com.example.starvelater.control.ICustomBottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RestaurantProfile extends AppCompatActivity {

    RecyclerView datalist;
    RecyclerView itemlist;

    List<String> titles;
    List<Integer> images;
    List<String> prices;

    List<String> itemNameList;
    List<Integer> itemPriceList;
    List<Integer> itemCountList;

    HashMap<String,Integer> nameCountList;
    HashMap<String,Integer> namePriceList;

    RecycleGridAdapter adapter;
    RestaurantItemAdapter itemAdapter;

    ImageView backbutton;
    Toolbar restaurantToolBar;

    int itemCost,totalCost, itemCount;

    TextView txtRestaurantName, txtRestaurantLocation,txtTotalCost;

    Switch aSwitch;

    LinearLayout linearLayout;

    private com.example.starvelater.control.BottomSheetBehavior mBottomSheetBehavior;
    private View mBottomSheetView;

    private CoordinatorLayout mParent;

    private RecyclerView mBottomSheetRecycler;
    private LinearLayoutManager mLayoutManager;
    private OrderedItemsAdapter mOrderedItemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

        datalist = findViewById(R.id.datalist);
        itemlist = findViewById(R.id.itemlist);

        nameCountList = new HashMap<>();
        namePriceList = new HashMap<>();


        txtRestaurantName = findViewById(R.id.restaurant_name);
        txtRestaurantLocation = findViewById(R.id.restaurant_location);
        txtTotalCost = findViewById(R.id.total_item_cost);

        txtTotalCost.setText("₹ 0.00");

        titles = new ArrayList<>();
        images = new ArrayList<>();
        prices = new ArrayList<>();

        itemNameList = new ArrayList<>();
        itemPriceList = new ArrayList<>();
        itemCountList = new ArrayList<>();

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


        //Bottom Sheet Code
        mParent = (CoordinatorLayout) findViewById(R.id.parent_container);
        mParent.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        mBottomSheetView = findViewById(R.id.main_bottomsheet);

        mBottomSheetBehavior = com.example.starvelater.control.BottomSheetBehavior.from(mBottomSheetView);

        mBottomSheetRecycler= (RecyclerView) findViewById(R.id.ordereditems_list);
        mLayoutManager = new LinearLayoutManager(this);
        mBottomSheetRecycler.setLayoutManager(mLayoutManager);

        mBottomSheetBehavior.setPeekHeight(100);
        mBottomSheetBehavior.setState(com.example.starvelater.control.BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new com.example.starvelater.control.BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    //  mBottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("item-details"));
        //LocalBroadcastManager.getInstance(this).registerReceiver(mItemCountReciever,new IntentFilter("item-count-details"));

            Bundle bundle = getIntent().getExtras();
            assert bundle != null;
            String restaurantName = bundle.getString("name");
            String restaurantLocation = bundle.getString("location");

            txtRestaurantName.setText(restaurantName);
            txtRestaurantLocation.setText(restaurantLocation);

        //helper to rule scrolls
        BottomSheetBehaviorRecyclerManager manager = new BottomSheetBehaviorRecyclerManager(mParent, mBottomSheetBehavior, mBottomSheetView);
        manager.addControl(mBottomSheetRecycler);
        manager.create();


        linearLayout = findViewById(R.id.linearLayout);

        restaurantToolBar = findViewById(R.id.restaurantToolBar);
        setSupportActionBar(restaurantToolBar);

        aSwitch = findViewById(R.id.toggleswitch);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(RestaurantProfile.this, "Show Vegetarian Items Only", Toast.LENGTH_SHORT).show();
                }
                if (!isChecked) {
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
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        adapter = new RecycleGridAdapter(this, titles, images,prices);

        itemAdapter = new RestaurantItemAdapter(this, titles, prices);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        itemlist.setLayoutManager(linearLayoutManager);
        itemlist.setAdapter((RecyclerView.Adapter) itemAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        datalist.setLayoutManager(gridLayoutManager);
        datalist.setAdapter((RecyclerView.Adapter) adapter);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // Get extra data included in the Intent
            String ItemName = intent.getStringExtra("item-name");
            String cost = intent.getStringExtra("item-cost");
            String count = intent.getStringExtra("item-count");
            String costnumber = cost.substring(2);

            assert costnumber != null;
            itemCost = Integer.parseInt(costnumber);
            assert count != null;
            itemCount = Integer.parseInt(count);

            if(!itemNameList.contains(ItemName) && !itemPriceList.contains(itemCost)) {
                //Addition of New Item to Array List
                itemNameList.add(ItemName);
                itemPriceList.add(itemCost);

                //add to hashmap both itemname and count default 1
               nameCountList.put(ItemName,itemCount);
               namePriceList.put(ItemName,itemCost);

                totalCost = totalCost + ((int) nameCountList.get(ItemName) * (int) namePriceList.get(ItemName));


            } else {
                int price = 0;
                price = totalCost - (int) namePriceList.get(ItemName);
                //old item already present in array list
                nameCountList.put(ItemName,nameCountList.get(ItemName) + 1);
                totalCost = price + ((int) nameCountList.get(ItemName) * (int) namePriceList.get(ItemName));

                //Toast.makeText(context, ""+ namePriceList + "\n name Count List" + nameCountList, Toast.LENGTH_LONG).show();
            }



/*            for (Map.Entry mapElement : namePriceList.entrySet()) {
                String key = (String) mapElement.getKey();
                Log.d("Total",key + " " + (int) namePriceList.get(key) + (int) nameCountList.get(key));
                Toast.makeText(context,key + " " + (int) namePriceList.get(key) + (int) nameCountList.get(key) , Toast.LENGTH_LONG).show();
                totalCost = totalCost +  ((int) nameCountList.get(key) * (int) namePriceList.get(key));
            }*/




            /*if(itemCount == 1) {
                totalCost = totalCost + itemCost;
            }
            else if(itemCount ==2) {
                int price =0;
                price = totalCost - itemCost;
                totalCost = price + itemCost * 2;
            } else if(itemCount ==3) {
                int price =0;
                price = totalCost - itemCost;
                totalCost = price + itemCost * 3;
            } else if(itemCount ==4) {
                int price=0;
                price = totalCost - itemCost;
                totalCost = price + itemCost * 4;
            } else if(itemCount ==5) {
                int price=0;
                price = totalCost - itemCost;
                totalCost = price + itemCost * 5;
            }
*/
            /*switch (itemCount) {
                case 1 : totalCost = totalCost + itemCost;
                         break;
                case 2 :

            }*/

            //totalCost = totalCost + itemCost * (itemCount);

            txtTotalCost.setText("₹ "+totalCost+ ".00");

            mOrderedItemsAdapter = new OrderedItemsAdapter(mBottomSheetBehavior,itemNameList,itemPriceList);
            mBottomSheetRecycler.setAdapter(mOrderedItemsAdapter);
        }
    };

   /* public BroadcastReceiver mItemCountReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent

            int count = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("each-item-count")));

           itemCountList.add(count);

           for(int i=0;i<itemCountList.size();i++)
            {
                int price = itemPriceList.get(i);
                int qty = itemCountList.get(i);
                totalCost = totalCost + qty * price;
            }

            txtTotalCost.setText("₹ "+totalCost+ ".00");

            mOrderedItemsAdapter = new OrderedItemsAdapter(mBottomSheetBehavior,itemNameList,itemPriceList);
            mBottomSheetRecycler.setAdapter(mOrderedItemsAdapter);
        }
    };*/



}