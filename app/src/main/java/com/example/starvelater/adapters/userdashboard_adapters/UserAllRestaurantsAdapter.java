package com.example.starvelater.adapters.userdashboard_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
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

            //Loading only Restaurants of 'All Restaurants' Category
            if(restaurantsList.get(position).getOperationStatus().equals("Closed")) {
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);

                Glide.with(context).load(restaurantsList.get(position).getRestaurantLogo()).into(holder.image);
                holder.image.setColorFilter(filter);
                //Glide.with(context).load(R.drawable.bill).into(holder.image);
                //holder.closed.setVisibility(View.VISIBLE);
            }else{
                Glide.with(context).load(restaurantsList.get(position).getRestaurantLogo()).into(holder.image);
            }
            /*Glide.with(context)
                .load(restaurantsList.get(position).getRestaurantLogo())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.image.setImageDrawable(resource);
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                        holder.progressBar.setVisibility(View.GONE);
                    }

                });*/
            //holder.image.setImageResource(popularHelperClass.getImage());
            holder.title.setText(restaurantsList.get(position).getRestaurant_Name());
            holder.description.setText(restaurantsList.get(position).getKnownFor());

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), RestaurantProfileActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("rest_ID",restaurantsList.get(position).getRestaurant_ID());
                    bundle.putString("operationStatus",restaurantsList.get(position).getOperationStatus());
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
                    bundle.putString("rest_ID",restaurantsList.get(position).getRestaurant_ID());
                    bundle.putString("operationStatus",restaurantsList.get(position).getOperationStatus());
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
        LinearLayout progressBar;
        TextView title, description;
        //ImageView closed;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            progressBar = itemView.findViewById(R.id.progressBar);
            image = itemView.findViewById(R.id.most_popular_image);
            //closed = itemView.findViewById(R.id.closed);
            title = itemView.findViewById(R.id.most_popular_title);
            description = itemView.findViewById(R.id.most_popular_description);

        }

    }
}

