package com.example.starvelater.adapters.restaurantprofile_adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.example.starvelater.interfaces.CartItemClickListener;
import com.example.starvelater.model.Product;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CategoryCardDesignAdapter extends RecyclerView.Adapter<CategoryCardDesignAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> itemsModelList;
    private CartItemClickListener cartItemClickListener;
    private String operationStatus;
    LayoutInflater inflater;

    public CategoryCardDesignAdapter(Context mContext, List<Product> itemsModelList,CartItemClickListener cartItemClickListener, String operationStatus) {

        this.mContext = mContext;
        this.itemsModelList = itemsModelList;
        this.cartItemClickListener = cartItemClickListener;
        this.operationStatus = operationStatus;

    }

    @NonNull
    @Override
    public CategoryCardDesignAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_design, parent, false);

        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CategoryCardDesignAdapter.ViewHolder holder, int position) {

        final Product categoryItemsModel = itemsModelList.get(position);

        if(categoryItemsModel.getType().equals("Vegetarian")){
            holder.title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_veg,0,0,0);
        }
        else{
            holder.title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_non_veg,0,0,0);
        }

        holder.title.setText(categoryItemsModel.getTitles());
        holder.price.setText("₹ "+categoryItemsModel.getUnitPrice());


        if(operationStatus.equals("Closed")) {
            // Disabling add Button Layout
            holder.addButtonLayout.setVisibility(View.GONE);

        } else {

            holder.productQuantity.setText(""+categoryItemsModel.getQuantity());

            if(categoryItemsModel.getQuantity() == 0) {
                holder.productQuantity.setText("ADD");
                holder.productMinus.setVisibility(View.GONE);
                holder.productPlus.setVisibility(View.GONE);
                Log.d(TAG, "onBindViewHolder: " + categoryItemsModel.getQuantity());
            } else {
                holder.productQuantity.setText("" + categoryItemsModel.getQuantity());
                holder.productMinus.setVisibility(View.VISIBLE);
                holder.productPlus.setVisibility(View.VISIBLE);
                Log.d(TAG, "onBindViewHolder1: " + categoryItemsModel.getQuantity());
            }

            holder.productQuantity.setOnClickListener(view -> {
                if (holder.productQuantity.getText().toString().equalsIgnoreCase("ADD")) {
                    holder.productQuantity.setText("" + categoryItemsModel.getQuantity());
                    holder.productMinus.setVisibility(View.VISIBLE);
                    holder.productPlus.setVisibility(View.VISIBLE);

                    cartItemClickListener.onAddItemClick(position,categoryItemsModel);

                }
            });

            holder.productMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cartItemClickListener.onItemMinusClick(categoryItemsModel);

                }
            });

            holder.productPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cartItemClickListener.onItemPlusClick(categoryItemsModel);

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return itemsModelList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        Button addItem;
        TextView title, price;
        TextView productMinus,productPlus,productQuantity;
        LinearLayout addButtonLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);
            productMinus= itemView.findViewById(R.id.product_minus);
            productPlus= itemView.findViewById(R.id.product_plus);
            productQuantity= itemView.findViewById(R.id.product_quantity);
            addButtonLayout = itemView.findViewById(R.id.quantityLayout);

        }
    }
}
