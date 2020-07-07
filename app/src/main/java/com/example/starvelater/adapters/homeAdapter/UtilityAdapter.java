package com.example.starvelater.adapters.homeAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.example.starvelater.activities.college_canteen.All_CollegeCanteen;
import com.example.starvelater.activities.corporate_cafe.All_CorporateCafe;
import com.example.starvelater.activities.restaurant.All_Restaurants;

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
    public void onBindViewHolder(@NonNull final UtilityAdapter.UtilityViewHolder holder, int position) {

        final UtilityHelperClass utilityHelperClass = utility.get(position);
        holder.title.setText(utilityHelperClass.getTitle());
        holder.image.setImageResource(utilityHelperClass.getImage());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.title.getText().equals("Restaurant")) {
                    Intent intent = new Intent(v.getContext(), All_Restaurants.class);
                    holder.title.getContext().startActivity(intent);
                }

                if(holder.title.getText().equals("College Canteen")) {
                    Intent intent = new Intent(v.getContext(), All_CollegeCanteen.class);
                    holder.title.getContext().startActivity(intent);
                }

                if(holder.title.getText().equals("Corporate Cafe")) {
                    Intent intent = new Intent(v.getContext(), All_CorporateCafe.class);
                    holder.title.getContext().startActivity(intent);
                }

            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.title.getText().equals("Restaurant")) {
                    Intent intent = new Intent(v.getContext(), All_Restaurants.class);
                    holder.image.getContext().startActivity(intent);
                }

                if(holder.title.getText().equals("College Canteen")) {
                    Intent intent = new Intent(v.getContext(), All_CollegeCanteen.class);
                    holder.image.getContext().startActivity(intent);
                }

                if(holder.title.getText().equals("Corporate Cafe")) {
                    Intent intent = new Intent(v.getContext(), All_CorporateCafe.class);
                    holder.image.getContext().startActivity(intent);
                }

            }
        });




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
