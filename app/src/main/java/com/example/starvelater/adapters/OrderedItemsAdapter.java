package com.example.starvelater.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.example.starvelater.activities.restaurant.RestaurantProfile;
import com.example.starvelater.control.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class OrderedItemsAdapter extends RecyclerView.Adapter<OrderedItemsAdapter.ViewHolder> {

    List<String> titles;
    List<String> prices;

    com.example.starvelater.control.BottomSheetBehavior inflater;

    public OrderedItemsAdapter(BottomSheetBehavior bottomSheetBehavior, List<String> titles, List<String> prices){
        this.titles = titles;
        this.prices = prices;
        this.inflater = bottomSheetBehavior;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.ordered_item_design,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.title.setText(titles.get(position));
        holder.price.setText(prices.get(position));
        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String count = holder.itemCount.getText().toString();
                int itemCount = Integer.parseInt(count) + 1;
                holder.itemCount.setText(""+itemCount);
                /*Intent intent = new Intent("item-count");
                intent.putExtra("each-item-count",""+itemCount);
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);*/

            }
        });
        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String count = holder.itemCount.getText().toString();
                int itemCount = Integer.parseInt(count);
                if(itemCount == 0)
                {
                    Toast.makeText(v.getContext(), "You have selected 0 Items", Toast.LENGTH_SHORT).show();
                }
                else{

                    int newitemCount = Integer.parseInt(count) - 1;
                    holder.itemCount.setText(""+newitemCount);
                    /*Intent intent = new Intent("item-count");
                    intent.putExtra("each-item-count",""+itemCount);
                    LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);*/
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Button addItem;
        TextView title;
        TextView price;
        Button decrement;
        Button increment;
        TextView itemCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);
            addItem = itemView.findViewById(R.id.add_item);
            decrement = itemView.findViewById(R.id.decrement);
            increment = itemView.findViewById(R.id.increment);
            itemCount = itemView.findViewById(R.id.itemCount);

        }
    }
}
