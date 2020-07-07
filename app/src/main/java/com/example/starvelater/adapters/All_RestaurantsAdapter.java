package com.example.starvelater.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.example.starvelater.activities.restaurant.RestaurantProfile;

import java.util.List;

public class All_RestaurantsAdapter extends RecyclerView.Adapter<All_RestaurantsAdapter.ViewHolder> {

    List<String> restauarantNames;
    List<String> restaurantLocation;
    List<Integer> restaurantImages;

    LayoutInflater inflater;

    public All_RestaurantsAdapter(Context ctx, List<String> restauarantNames, List<String> restaurantLocation,List<Integer> restaurantImages){
        this.restauarantNames = restauarantNames;
        this.restaurantLocation = restaurantLocation;
        this.restaurantImages = restaurantImages;
        this.inflater = LayoutInflater.from(ctx);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.all_restaurants_card,parent,false);
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

        holder.restName.setText(restauarantNames.get(position));
        holder.restLocation.setText(restaurantLocation.get(position));
        holder.restImage.setImageResource(restaurantImages.get(position));

        holder.restImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RestaurantProfile.class);
                holder.restImage.getContext().startActivity(intent);
            }
        });

        holder.restName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RestaurantProfile.class);
                holder.restName.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return restauarantNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView restName;
        TextView restLocation;
        ImageView restImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restName = itemView.findViewById(R.id.restaurant_name);
            restLocation = itemView.findViewById(R.id.restaurant_location);
            restImage = itemView.findViewById(R.id.restaurant_image);
        }
    }
}
