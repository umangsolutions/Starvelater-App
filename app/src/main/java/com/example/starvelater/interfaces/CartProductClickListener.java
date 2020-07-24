package com.example.starvelater.interfaces;

import com.example.starvelater.model.Product;

public interface CartProductClickListener {

    void onMinusClick(Product product);

    void onPlusClick(Product product);

    void onAddClick(int position,Product product);
}
