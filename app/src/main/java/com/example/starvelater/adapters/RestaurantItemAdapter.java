package com.example.starvelater.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;

import java.util.List;

public class RestaurantItemAdapter extends RecyclerView.Adapter<RestaurantItemAdapter.ViewHolder> {

    List<String> titles;
    List<String> prices;

    LayoutInflater inflater;

    public RestaurantItemAdapter(Context ctx, List<String> titles, List<String> prices){
        this.titles = titles;
        this.prices = prices;
        this.inflater = LayoutInflater.from(ctx);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_card_design,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.title.setText(titles.get(position));
        holder.price.setText(prices.get(position));
        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String itemName = holder.title.getText().toString();
                String itemCost = holder.price.getText().toString();

                Intent intent = new Intent("item-details");
                intent.putExtra("item-name",itemName);
                intent.putExtra("item-cost",itemCost);

                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);

                holder.addItem.setVisibility(View.GONE);
                holder.txtOrderedText.setVisibility(View.VISIBLE);


                Toast.makeText(v.getContext(), "Item Added Successfully !", Toast.LENGTH_SHORT).show();


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
        TextView txtOrderedText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);
            addItem = itemView.findViewById(R.id.add_item);
            txtOrderedText = itemView.findViewById(R.id.ordered_text_view);

        }
    }
}
