package com.example.starvelater.adapters.userdashboard_adapters;

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
import com.example.starvelater.jsonmodels.PopularRestaurantsModel;
import com.example.starvelater.jsonmodels.RestaurantsModel;

import java.util.List;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MyViewHolder> {

    Context context;
    List<RestaurantsModel.DataBean> featuredRestaurants;

    public MostPopularAdapter(Context context, List<RestaurantsModel.DataBean> featuredRestaurants) {
        this.context = context;
        this.featuredRestaurants = featuredRestaurants;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

            // loading only Restaurants of type 'Most Popular'

        if(featuredRestaurants.get(position).getOperationStatus().equals("Closed")) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

            Glide.with(context).load(featuredRestaurants.get(position).getRestaurantLogo()).into(holder.image);
            holder.image.setColorFilter(filter);
            //Glide.with(context).load(R.drawable.bill).into(holder.image);
            //holder.closed.setVisibility(View.VISIBLE);
        }else{
            Glide.with(context).load(featuredRestaurants.get(position).getRestaurantLogo()).into(holder.image);
        }

            holder.title.setText(featuredRestaurants.get(position).getRestaurant_Name());
            holder.description.setText(featuredRestaurants.get(position).getAddress());

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), RestaurantProfileActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("rest_ID",featuredRestaurants.get(position).getRestaurant_ID());
                    bundle.putString("operationStatus",featuredRestaurants.get(position).getOperationStatus());
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
                    bundle.putString("rest_ID",featuredRestaurants.get(position).getRestaurant_ID());
                    bundle.putString("operationStatus",featuredRestaurants.get(position).getOperationStatus());
                    bundle.putString("name", holder.title.getText().toString());
                    bundle.putString("location", holder.description.getText().toString());
                    intent.putExtras(bundle);

                    holder.title.getContext().startActivity(intent);
                }
            });



    }

    @Override
    public int getItemCount() {


        return  featuredRestaurants.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, description,hiddenID;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            description = itemView.findViewById(R.id.featured_description);

        }
    }
}
