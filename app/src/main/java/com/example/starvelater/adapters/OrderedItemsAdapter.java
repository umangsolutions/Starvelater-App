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
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.nio.charset.IllegalCharsetNameException;
import java.util.HashMap;
import java.util.List;

public class OrderedItemsAdapter extends RecyclerView.Adapter<OrderedItemsAdapter.ViewHolder> {

    List<String> titles;
    HashMap<String, Integer> prices;
    HashMap<String,Integer> count;

    com.example.starvelater.control.BottomSheetBehavior inflater;

    public OrderedItemsAdapter(BottomSheetBehavior bottomSheetBehavior, List<String> titles, HashMap<String, Integer> prices,
                               HashMap<String, Integer> count) {
        this.titles = titles;
        this.prices = prices;
        this.count = count;
        this.inflater = bottomSheetBehavior;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordered_item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.title.setText(titles.get(position));
        holder.price.setText("₹ " +Integer.toString((int)count.get(titles.get(position)) * (int)prices.get(titles.get(position))));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textItemName);
            price = itemView.findViewById(R.id.textItemPrice);

        }
    }
}


