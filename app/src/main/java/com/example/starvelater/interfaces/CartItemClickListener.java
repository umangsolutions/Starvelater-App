package com.example.starvelater.interfaces;

import com.example.starvelater.model.NormalProducts;
import com.example.starvelater.model.Product;

public interface CartItemClickListener {

    void onItemMinusClick(NormalProducts product);

    void onItemPlusClick(NormalProducts product);

    void onAddItemClick(int position,NormalProducts product);

}
