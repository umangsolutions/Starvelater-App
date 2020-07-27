package com.example.starvelater.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starvelater.R;
import com.example.starvelater.interfaces.CartItemClickListener;
import com.example.starvelater.model.NormalProducts;
import com.example.starvelater.model.Product;

import java.util.List;

import static android.content.ContentValues.TAG;

public class OrderedItemsAdapter extends RecyclerView.Adapter<OrderedItemsAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> recommendedModelList;
    private List<NormalProducts> normalModelList;

    LayoutInflater inflater;

    public OrderedItemsAdapter(Context mContext, List<Product> recommendedModelList, List<NormalProducts> normalModelList) {

        this.mContext = mContext;
        this.recommendedModelList = recommendedModelList;
        this.normalModelList = normalModelList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_design, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Product recommendedProductModel = recommendedModelList.get(position);
        final NormalProducts normalProductsModel = normalModelList.get(position);

        holder.title.setText(normalProductsModel.getTitles());
        holder.price.setText("₹ "+normalProductsModel.getUnitPrice());
        //  holder.productQuantity.setText(""+productsModel.getQuantity());

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


        }
    }
}
