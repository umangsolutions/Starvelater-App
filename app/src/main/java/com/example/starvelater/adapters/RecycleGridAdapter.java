package com.example.starvelater.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.List;

public class RecycleGridAdapter extends RecyclerView.Adapter<RecycleGridAdapter.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    List<String> prices;

    LayoutInflater inflater;

    public RecycleGridAdapter(Context ctx, List<String> titles, List<Integer> images, List<String> prices) {
        this.titles = titles;
        this.images = images;
        this.prices = prices;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_custom_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.title.setText(titles.get(position));
        holder.gridIcon.setImageResource(images.get(position));
        holder.price.setText(prices.get(position));
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final String itemName = holder.title.getText().toString();
                final String itemCost = holder.price.getText().toString();

                final Intent intent = new Intent("item-details");
                intent.putExtra("item-name", itemName);
                intent.putExtra("item-cost", itemCost);
                holder.btnAdd.setVisibility(View.GONE);
                holder.numberPicker.setVisibility(View.VISIBLE);

                intent.putExtra("item-count", "1");

                holder.numberPicker.setValueChangedListener(new ValueChangedListener() {
                    @Override
                    public void valueChanged(int value, ActionEnum action) {

                        String actionText = action == ActionEnum.MANUAL ? "manually set" : (action == ActionEnum.INCREMENT ? "incremented" : "decremented");
                        intent.putExtra("item-name", itemName);
                        intent.putExtra("item-cost", itemCost);
                        intent.putExtra("item-action", actionText);
                        intent.putExtra("item-count", Integer.toString(value));
                        if (value == 0) {
                            holder.btnAdd.setVisibility(View.VISIBLE);
                            holder.numberPicker.setVisibility(View.GONE);
                        }
                        LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
                    }
                });

                Toast.makeText(v.getContext(), "Item Added Successfully !", Toast.LENGTH_SHORT).show();

                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, price;
        ImageView gridIcon;
        Button btnAdd;
        NumberPicker numberPicker;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            gridIcon = itemView.findViewById(R.id.imageView);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            price = itemView.findViewById(R.id.price);
            numberPicker = itemView.findViewById(R.id.number_picker);
        }

    }
}
