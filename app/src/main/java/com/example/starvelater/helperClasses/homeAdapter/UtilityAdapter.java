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

public class UtilityAdapter extends RecyclerView.Adapter<UtilityAdapter.UtilityViewHolder> {

    ArrayList<UtilityHelperClass> utility;

    public UtilityAdapter(ArrayList<UtilityHelperClass> utility) {
        this.utility = utility;
    }

    @NonNull
    @Override
    public UtilityAdapter.UtilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_utility_card_design, parent, false);
        UtilityViewHolder utilityViewHolder = new UtilityViewHolder(view);
        return utilityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UtilityAdapter.UtilityViewHolder holder, int position) {

        UtilityHelperClass utilityHelperClass = utility.get(position);
        holder.title.setText(utilityHelperClass.getTitle());
        holder.image.setImageResource(utilityHelperClass.getImage());


    }

    @Override
    public int getItemCount() {

        return utility.size();
    }

    public static class UtilityViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        public UtilityViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.multi_utility_icon);
            title = itemView.findViewById(R.id.multi_utility_title);


        }
    }
}
