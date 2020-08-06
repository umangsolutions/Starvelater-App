package com.example.starvelater.interfaces;

import com.example.starvelater.jsonmodels.CategoryItemsModel;
import com.example.starvelater.model.NormalProducts;
import com.example.starvelater.model.Product;

public interface CartItemClickListener {

    void onItemMinusClick(Product product);

    void onItemPlusClick(Product product);

    void onAddItemClick(int position,Product product);

}
