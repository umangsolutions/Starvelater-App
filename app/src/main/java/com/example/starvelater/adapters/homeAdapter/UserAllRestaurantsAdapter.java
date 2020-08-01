package com.example.starvelater.adapters.homeAdapter;

import android.content.Context;
import android.content.Intent;
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

public class UserAllRestaurantsAdapter extends RecyclerView.Adapter<UserAllRestaurantsAdapter.MyViewHolder> {

    Context context;
    List<RestaurantsModel.DataBean> restaurantsList;

    public UserAllRestaurantsAdapter(Context context, List<RestaurantsModel.DataBean> restaurantsList) {
        this.context = context;
        this.restaurantsList = restaurantsList ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.most_popular_card_design, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {


        Glide.with(context).load(restaurantsList.get(position).getRestaurantLogo()).into(holder.image);
        //holder.image.setImageResource(popularHelperClass.getImage());
        holder.title.setText(restaurantsList.get(position).getRestaurant_Name());
        holder.description.setText(restaurantsList.get(position).getKnownFor());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", holder.title.getText().toString());
                bundle.putString("location", holder.description.getText().toString());
                intent.putExtras(bundle);
                holder.image.getContext().startActivity(intent);
            }
        });

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", holder.title.getText().toString());
                bundle.putString("location", holder.description.getText().toString());
                intent.putExtras(bundle);
                holder.title.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return restaurantsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.most_popular_image);
            title = itemView.findViewById(R.id.most_popular_title);
            description = itemView.findViewById(R.id.most_popular_description);

        }

    }
}

