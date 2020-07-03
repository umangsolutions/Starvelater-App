package com.example.starvelater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.starvelater.helperClasses.RecycleGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResturanteGalery extends AppCompatActivity {
    RecyclerView datalist;
    List<String> titles;
    List<Integer> images;
    RecycleGridAdapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturante_galery);
        datalist=findViewById(R.id.datalist);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Beverages");
        titles.add("Biriyani north india");
        titles.add("red cherry");
        titles.add("Italian Fast Food");
        titles.add("Amarican Italian Food");
        titles.add("Beverages");

        titles.add("Beverages");
        titles.add("Biriyani north india");
        titles.add("red cherry");
        titles.add("Italian Fast Food");
        titles.add("Amarican Italian Food");
        titles.add("Biriyani north india");


        images.add(R.drawable.photo6);
        images.add(R.drawable.photo7);
        images.add(R.drawable.photo8);
        images.add(R.drawable.photo9);
        images.add(R.drawable.photo10);
        images.add(R.drawable.photo11);

        images.add(R.drawable.photo6);
        images.add(R.drawable.photo7);
        images.add(R.drawable.photo8);
        images.add(R.drawable.photo9);
        images.add(R.drawable.photo10);
        images.add(R.drawable.photo11);


         adapter = new RecycleGridAdapter(this,titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        datalist.setLayoutManager(gridLayoutManager);
        datalist.setAdapter((RecyclerView.Adapter) adapter);
    }
}