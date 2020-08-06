package com.example.starvelater.adapters.restaurantprofile_adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starvelater.R;
import com.example.starvelater.interfaces.CartProductClickListener;
import com.example.starvelater.model.Product;
import java.util.List;

import static android.content.ContentValues.TAG;

public class RecycleGridAdapter1 extends RecyclerView.Adapter<RecycleGridAdapter1.ViewHolder> {

    private Context mContext;
    private List<Product> productsModelList;
    private CartProductClickListener productClickListener;

    LayoutInflater inflater;

    public RecycleGridAdapter1(Context mContext, List<Product> productsModelList, CartProductClickListener productClickListener) {
        this.mContext = mContext;
        this.productsModelList = productsModelList;
        this.productClickListener = productClickListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_custom_grid_layout1, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Product productsModel = productsModelList.get(position);

        holder.title.setText(productsModel.getTitles());

       Glide.with(mContext).load(productsModel.getImages()).into(holder.gridIcon);
        //holder.gridIcon.setImageResource(R.drawable.bbqnation);
        holder.price.setText("₹ "+productsModel.getUnitPrice());
      //  holder.productQuantity.setText(""+productsModel.getQuantity());

        if (productsModel.getQuantity() == 0) {
            holder.productQuantity.setText("ADD");
            holder.productMinus.setVisibility(View.GONE);
            holder.productPlus.setVisibility(View.GONE);
            Log.d(TAG, "onBindViewHolder: " + productsModel.getQuantity());
        } else {
            holder.productQuantity.setText("" + productsModel.getQuantity());
            holder.productMinus.setVisibility(View.VISIBLE);
            holder.productPlus.setVisibility(View.VISIBLE);
            Log.d(TAG, "onBindViewHolder1: " + productsModel.getQuantity());
        }

        holder.productQuantity.setOnClickListener(view -> {
            if (holder.productQuantity.getText().toString().equalsIgnoreCase("ADD")) {
                holder.productQuantity.setText("" + productsModel.getQuantity());
                holder.productMinus.setVisibility(View.VISIBLE);
                holder.productPlus.setVisibility(View.VISIBLE);
                productClickListener.onAddClick(position, productsModel);
            }
        });



        holder.productMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                productClickListener.onMinusClick(productsModel);
            }
        });

        holder.productPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                productClickListener.onPlusClick(productsModel);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productsModelList.size();
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

        TextView title, price;
        ImageView gridIcon;
        Button btnAdd;
        TextView productMinus,productPlus,productQuantity;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            gridIcon = itemView.findViewById(R.id.imageView);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            price = itemView.findViewById(R.id.price);
            productMinus= itemView.findViewById(R.id.product_minus);
            productPlus= itemView.findViewById(R.id.product_plus);
            productQuantity= itemView.findViewById(R.id.product_quantity);

        }

    }
}
