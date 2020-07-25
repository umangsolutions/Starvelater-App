package com.example.starvelater.activities.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.example.starvelater.activities.user.UserDashboard;
import com.example.starvelater.adapters.RecycleGridAdapter1;
import com.example.starvelater.adapters.RestaurantItemAdapter;
import com.example.starvelater.interfaces.CartItemClickListener;
import com.example.starvelater.interfaces.CartProductClickListener;
import com.example.starvelater.model.NormalProducts;
import com.example.starvelater.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantProfileActivity extends AppCompatActivity implements CartProductClickListener, CartItemClickListener {

    RecyclerView datalist;
    RecyclerView itemlist;

    CartProductClickListener cartProductClickListener;
    CartItemClickListener cartItemClickListener;
    List<Product> productArrayList = new ArrayList<>();
    List<NormalProducts> itemsArrayList = new ArrayList<>();

    List<String> itemNameList;
    List<Integer> itemPriceList;
    List<Integer> itemCountList;

    HashMap<String, Integer> nameCountList;
    HashMap<String, Integer> namePriceList;

    RecycleGridAdapter1 adapter;
    RestaurantItemAdapter itemAdapter;
    FloatingActionButton fabCart;

    ImageView backbutton;
    Toolbar restaurantToolBar;
    int itemCost, totalCost, itemCount;

    TextView txtRestaurantName, txtRestaurantLocation, txtTotalCost;

    Switch aSwitch;

    LinearLayout linearLayout;

    private View mBottomSheetView;

    private CoordinatorLayout mParent;

    private RecyclerView mBottomSheetRecycler;
    private LinearLayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile_activity);

        cartProductClickListener = (CartProductClickListener) this;
        cartItemClickListener = (CartItemClickListener) this;

        itemsArrayList.add(new NormalProducts(600,600,0,"Veg Chowmein"));
        itemsArrayList.add(new NormalProducts(200,200,0,"Veg Manchurian"));
        itemsArrayList.add(new NormalProducts(100,100,0,"Schezwan Soup"));
        itemsArrayList.add(new NormalProducts(300,300,0,"Masala Kulcha"));

        productArrayList.add(new Product(500,500,0,"Beverages",R.drawable.photo6));
        productArrayList.add(new Product(1500,1500,0,"Biriyani north india",R.drawable.photo7));
        productArrayList.add(new Product(250,250,0,"red cherry",R.drawable.photo8));
        productArrayList.add(new Product(369,369,0,"Italian Fast Food",R.drawable.photo9));
        productArrayList.add(new Product(400,400,0,"American Italian Food",R.drawable.photo10));
        productArrayList.add(new Product(128,128,0,"Masala Kulcha",R.drawable.photo11));


        datalist = findViewById(R.id.datalist);
        itemlist = findViewById(R.id.itemlist);

        nameCountList = new HashMap<>();
        namePriceList = new HashMap<>();


        txtRestaurantName = findViewById(R.id.restaurant_name);
        txtRestaurantLocation = findViewById(R.id.restaurant_location);

        /*fabCart = findViewById(R.id.fab_cart);
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RestaurantProfileActivity.this, "Cart", Toast.LENGTH_SHORT).show();
            }
        });*/

        itemNameList = new ArrayList<>();
        itemPriceList = new ArrayList<>();
        itemCountList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String restaurantName = bundle.getString("name");
        String restaurantLocation = bundle.getString("location");

        txtRestaurantName.setText(restaurantName);
        txtRestaurantLocation.setText(restaurantLocation);
        linearLayout = findViewById(R.id.linearLayout);

        restaurantToolBar = findViewById(R.id.restaurantToolBar);
        setSupportActionBar(restaurantToolBar);

        aSwitch = findViewById(R.id.toggleswitch);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(RestaurantProfileActivity.this, "Show Vegetarian Items Only", Toast.LENGTH_SHORT).show();
                }
                if (!isChecked) {
                    Toast.makeText(RestaurantProfileActivity.this, "Show All Items", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final ActionBar restaurantActionBar = getSupportActionBar();
        restaurantActionBar.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        restaurantActionBar.setDisplayShowTitleEnabled(false);

        backbutton = (ImageView) findViewById(R.id.back_button);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantProfileActivity.this, UserDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        adapter = new RecycleGridAdapter1(this, productArrayList,cartProductClickListener);

       itemAdapter = new RestaurantItemAdapter(this, itemsArrayList, cartItemClickListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        itemlist.setLayoutManager(linearLayoutManager);
        itemlist.setAdapter((RecyclerView.Adapter) itemAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        datalist.setLayoutManager(gridLayoutManager);
        datalist.setAdapter((RecyclerView.Adapter) adapter);

        calculateCartTotal();
    }



    @Override
    public void onMinusClick(Product cartItemsBean) {
        int i = productArrayList.indexOf(cartItemsBean);
        if (cartItemsBean.getQuantity() > 0) {
            int itemTotal = cartItemsBean.getUnitPrice() * cartItemsBean.getQuantity();

            Product updatedProduct = new Product(cartItemsBean.getUnitPrice(),itemTotal, (cartItemsBean.getQuantity() -1), cartItemsBean.getTitles(),
                    cartItemsBean.getImages());

            productArrayList.remove(cartItemsBean);
            productArrayList.add(i, updatedProduct);

            adapter.notifyDataSetChanged();

            calculateCartTotal();
        }
    }

    @Override
    public void onPlusClick(Product cartItemsBean) {
        int i = productArrayList.indexOf(cartItemsBean);

        int quantity = cartItemsBean.getQuantity() + 1;
        int itemTotal = cartItemsBean.getUnitPrice() * quantity;
        Log.d("TAG", "onMinusClick: "+quantity);


        Product updatedProduct = new Product(cartItemsBean.getUnitPrice(),itemTotal, quantity, cartItemsBean.getTitles(),
                cartItemsBean.getImages());

        productArrayList.remove(cartItemsBean);
        productArrayList.add(i, updatedProduct);

        Log.e("QUNATITY", "" + updatedProduct.getQuantity());
       // updateQuantity(updatedProduct);

        adapter.notifyDataSetChanged();


        calculateCartTotal();
    }

    @Override
    public void onAddClick(int position, Product cartItemsBean) {

        int i = productArrayList.indexOf(cartItemsBean);
        Product updatedProduct = new Product(cartItemsBean.getUnitPrice(),cartItemsBean.getUnitPrice(), 1, cartItemsBean.getTitles(),
                cartItemsBean.getImages());

        productArrayList.remove(cartItemsBean);
        productArrayList.add(i, updatedProduct);

        Log.e("QUNATITY", "" + updatedProduct.getQuantity());
        // updateQuantity(updatedProduct);

        adapter.notifyDataSetChanged();


        calculateCartTotal();
    }

    @Override
    public void onItemMinusClick(NormalProducts productItemsBean) {

        int i = itemsArrayList.indexOf(productItemsBean);
        if (productItemsBean.getQuantity() > 0) {
            int itemTotal = productItemsBean.getUnitPrice() * productItemsBean.getQuantity();

            NormalProducts updatedItem = new NormalProducts(productItemsBean.getUnitPrice(),itemTotal, (productItemsBean.getQuantity() -1), productItemsBean.getTitles());

            itemsArrayList.remove(productItemsBean);
            itemsArrayList.add(i, updatedItem);

            adapter.notifyDataSetChanged();

            calculateCartTotal();
        }

    }

    @Override
    public void onItemPlusClick(NormalProducts productItemsBean) {

        int i = productArrayList.indexOf(productItemsBean);

        int quantity = productItemsBean.getQuantity() + 1;
        int itemTotal = productItemsBean.getUnitPrice() * quantity;
        Log.d("TAG", "onMinusClick: "+quantity);


        NormalProducts updatedItem = new NormalProducts(productItemsBean.getUnitPrice(),itemTotal, quantity, productItemsBean.getTitles());

        itemsArrayList.remove(productItemsBean);
        itemsArrayList.add(i, updatedItem);

        Log.e("QUNATITY", "" + updatedItem.getQuantity());
        // updateQuantity(updatedProduct);

        adapter.notifyDataSetChanged();


        calculateCartTotal();


    }

    @Override
    public void onAddItemClick(int position, NormalProducts productItemsBean) {

        int i = productArrayList.indexOf(productItemsBean);
        NormalProducts updatedItem = new NormalProducts(productItemsBean.getUnitPrice(),productItemsBean.getUnitPrice(), 1, productItemsBean.getTitles());

        itemsArrayList.remove(productItemsBean);
        itemsArrayList.add(i, updatedItem);

        Log.e("QUNATITY", "" + updatedItem.getQuantity());
        // updateQuantity(updatedProduct);

        adapter.notifyDataSetChanged();


        calculateCartTotal();

    }

    // total Amount
    public void calculateCartTotal() {

        int grandTotal = 0;

        for (Product order : productArrayList) {

            grandTotal += (ParseDouble(String.valueOf(order.getUnitPrice())) * order.getQuantity());

        }

        for (NormalProducts order : itemsArrayList) {

            grandTotal += (ParseDouble(String.valueOf(order.getUnitPrice())) * order.getQuantity());

        }

        Log.e("TOTAL", "" + productArrayList.size() + "Items | " + "  DISCOUNT : " + grandTotal);

        //txtSubTotal.setText(productArrayList.size() + " Items | Rs " + grandTotal);


    }


    private double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else return 0;
    }

}