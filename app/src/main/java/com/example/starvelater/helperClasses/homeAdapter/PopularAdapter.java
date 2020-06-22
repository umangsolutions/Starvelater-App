package com.example.starvelater.helperClasses.homeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    ArrayList<PopularHelperClass> popularRestaurants;

    public PopularAdapter(ArrayList<PopularHelperClass> popularRestaurants) {
        this.popularRestaurants = popularRestaurants;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_popular_card_design, parent, false);
        PopularViewHolder popularViewHolder = new PopularViewHolder(view);
        return popularViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {

        PopularHelperClass popularHelperClass = popularRestaurants.get(position);
        holder.image.setImageResource(popularHelperClass.getImage());
        holder.title.setText(popularHelperClass.getTitle());
        holder.description.setText(popularHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {

        return popularRestaurants.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,description;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.most_popular_image);
            title = itemView.findViewById(R.id.most_popular_title);
            description = itemView.findViewById(R.id.most_popular_description);

        }

    }
}

