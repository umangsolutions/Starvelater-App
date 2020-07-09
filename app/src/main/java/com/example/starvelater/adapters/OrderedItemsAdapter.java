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
import java.util.List;

public class OrderedItemsAdapter extends RecyclerView.Adapter<OrderedItemsAdapter.ViewHolder> {

    List<String> titles;
    List<Integer> prices;

    com.example.starvelater.control.BottomSheetBehavior inflater;

    public OrderedItemsAdapter(BottomSheetBehavior bottomSheetBehavior, List<String> titles, List<Integer> prices){
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
        holder.price.setText(Integer.toString(prices.get(position)));

        holder.number_picker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {

                String itemCount = Integer.toString(value);

                Intent intent = new Intent("item-count-details");
                intent.putExtra("each-item-count",itemCount);


                LocalBroadcastManager.getInstance(holder.number_picker.getContext()).sendBroadcast(intent);

                //Toast.makeText(holder.number_picker.getContext(), ""+ value, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView price;
        NumberPicker number_picker;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);
            number_picker = itemView.findViewById(R.id.number_picker);

        }
    }
}


