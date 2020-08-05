package com.example.starvelater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.interfaces.CartItemClickListener;
import com.example.starvelater.jsonmodels.CategoryItemsModel;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CategoryCardDesignAdapter extends RecyclerView.Adapter<CategoryCardDesignAdapter.ViewHolder> {

    private Context mContext;
    private List<CategoryItemsModel.DataBean> itemsModelList;
    //private CartItemClickListener cartItemClickListener;
    LayoutInflater inflater;

    public CategoryCardDesignAdapter(Context mContext, List<CategoryItemsModel.DataBean> itemsModelList) {

        this.mContext = mContext;
        this.itemsModelList = itemsModelList;
        //this.cartItemClickListener = cartItemClickListener;

    }

    @NonNull
    @Override
    public CategoryCardDesignAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_design, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCardDesignAdapter.ViewHolder holder, int position) {

        final CategoryItemsModel.DataBean categoryItemsModel = itemsModelList.get(position);

        holder.title.setText(categoryItemsModel.getItem_Name());
        holder.price.setText("₹ "+categoryItemsModel.getItem_Price());
        //  holder.productQuantity.setText(""+productsModel.getQuantity());

        /*if (categoryItemsModel.getQuantity() == 0) {
            holder.productQuantity.setText("ADD");
            holder.productMinus.setVisibility(View.GONE);
            holder.productPlus.setVisibility(View.GONE);
            Log.d(TAG, "onBindViewHolder: " + normalProductsModel.getQuantity());
        } else {
            holder.productQuantity.setText("" + normalProductsModel.getQuantity());
            holder.productMinus.setVisibility(View.VISIBLE);
            holder.productPlus.setVisibility(View.VISIBLE);
            Log.d(TAG, "onBindViewHolder1: " + normalProductsModel.getQuantity());
        }

        holder.productQuantity.setOnClickListener(view -> {
            if (holder.productQuantity.getText().toString().equalsIgnoreCase("ADD")) {
                holder.productQuantity.setText("" + normalProductsModel.getQuantity());
                holder.productMinus.setVisibility(View.VISIBLE);
                holder.productPlus.setVisibility(View.VISIBLE);
                cartItemClickListener.onAddItemClick(position, normalProductsModel);
            }
        });



        holder.productMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cartItemClickListener.onItemMinusClick(normalProductsModel);
            }
        });

        holder.productPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cartItemClickListener.onItemPlusClick(normalProductsModel);
            }
        });*/

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);
            productMinus= itemView.findViewById(R.id.product_minus);
            productPlus= itemView.findViewById(R.id.product_plus);
            productQuantity= itemView.findViewById(R.id.product_quantity);

        }
    }
}
