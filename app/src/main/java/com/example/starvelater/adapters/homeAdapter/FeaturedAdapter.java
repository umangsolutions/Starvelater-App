package com.example.starvelater.adapters.homeAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.example.starvelater.activities.restaurant.RestaurantProfileActivity;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass> featuredRestaurants;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredRestaurants) {
        this.featuredRestaurants = featuredRestaurants;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FeaturedViewHolder holder, final int position) {

        final FeaturedHelperClass featuredHelperClass = featuredRestaurants.get(position);
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.description.setText(featuredHelperClass.getDescription());

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

        return featuredRestaurants.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, description;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            description = itemView.findViewById(R.id.featured_description);

        }
    }
}
