package com.example.starvelater.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.example.starvelater.interfaces.CartItemClickListener;
import com.example.starvelater.model.NormalProducts;
import com.example.starvelater.model.Product;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static android.content.ContentValues.TAG;

public class OrderedItemsAdapter extends RecyclerView.Adapter<OrderedItemsAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> recommendedModelList;

    LayoutInflater inflater;

    public OrderedItemsAdapter(Context mContext, List<Product> recommendedModelList) {

        this.mContext = mContext;
        this.recommendedModelList = recommendedModelList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ordered_item_card_design, parent, false);

        return new ViewHolder(itemView);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        //final Product recommendedProductModel = recommendedModelList.get(position);
       // normalModelList.addAll(recommendedModelList);


        final Product normalProductsModel = recommendedModelList.get(position);

         if(normalProductsModel.getQuantity() != 0 ) {
             holder.itemName.setText(normalProductsModel.getTitles());
             holder.count.setText(normalProductsModel.getQuantity() + " no.s");
             holder.finalprice.setText("₹ " + normalProductsModel.getItemTotalPrice());
         }

        //  holder.productQuantity.setText(""+productsModel.getQuantity());

    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
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

        TextView itemName,count,finalprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            count = itemView.findViewById(R.id.count);
            finalprice = itemView.findViewById(R.id.price);


        }
    }
}
