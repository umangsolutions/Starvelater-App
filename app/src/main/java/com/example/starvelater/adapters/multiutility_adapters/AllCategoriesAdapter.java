package com.example.starvelater.adapters.multiutility_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starvelater.R;
import com.example.starvelater.activities.restaurant.RestaurantProfileActivity;
import com.example.starvelater.jsonmodels.RestaurantsModel;

import java.util.List;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.ViewHolder> {


    Context context;
    List<RestaurantsModel.DataBean> restaurantsList;
    LayoutInflater inflater;

    public AllCategoriesAdapter(Context ctx, List<RestaurantsModel.DataBean> restaurantsList)  {
        this.context = ctx;
        this.restaurantsList = restaurantsList;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.all_restaurants_card, parent, false);
       /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantProfile.class);
                v.getContext().startActivity(intent);
            }
        });*/

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        if(restaurantsList.get(position).getOperationStatus().equals("Closed")) {

            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

            Glide.with(context).load(restaurantsList.get(position).getRestaurantLogo()).into(holder.restImage);
            holder.restImage.setColorFilter(filter);
            //Glide.with(context).load(R.drawable.bill).into(holder.image);
            //holder.closed.setVisibility(View.VISIBLE);
        }else{
            Glide.with(context).load(restaurantsList.get(position).getRestaurantLogo()).into(holder.restImage);
        }

        holder.restName.setText(restaurantsList.get(position).getRestaurant_Name());
        holder.restLocation.setText(restaurantsList.get(position).getAddress());
        holder.restavgPrepTime.setText(restaurantsList.get(position).getAvgPrepTime() + " mins");


        holder.restImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("rest_ID",restaurantsList.get(position).getRestaurant_ID());
                bundle.putString("operationStatus",restaurantsList.get(position).getOperationStatus());
                bundle.putString("name", holder.restName.getText().toString());
                bundle.putString("location", holder.restLocation.getText().toString());
                intent.putExtras(bundle);
                holder.restImage.getContext().startActivity(intent);
            }
        });

        holder.restName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("rest_ID",restaurantsList.get(position).getRestaurant_ID());
                bundle.putString("operationStatus",restaurantsList.get(position).getOperationStatus());
                bundle.putString("name", holder.restName.getText().toString());
                bundle.putString("location", holder.restLocation.getText().toString());
                intent.putExtras(bundle);
                holder.restName.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView restName;
        TextView restLocation;
        ImageView restImage;
        TextView restavgPrepTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restName = itemView.findViewById(R.id.restaurant_name);
            restLocation = itemView.findViewById(R.id.restaurant_location);
            restImage = itemView.findViewById(R.id.restaurant_image);
            restavgPrepTime = itemView.findViewById(R.id.restaurant_avgPrepTime);
        }
    }
}
