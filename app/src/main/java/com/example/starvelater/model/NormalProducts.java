package com.example.starvelater.model;

import java.io.Serializable;

public class NormalProducts extends Product implements Serializable {

    private int unitPrice;
    private int itemTotalPrice;
    private int quantity;
    private String titles;

    private String images;
    private String itemCategory;


    public NormalProducts() {

    }


    public NormalProducts(int unitPrice, int itemTotalPrice, int quantity, String titles, String images, String itemCategory) {
        this.unitPrice = unitPrice;
        this.itemTotalPrice = itemTotalPrice;
        this.quantity = quantity;
        this.titles = titles;
        this.images = images;
        this.itemCategory = itemCategory;
    }

    @Override
    public int getUnitPrice() {
        return unitPrice;
    }

    @Override
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public int getItemTotalPrice() {
        return itemTotalPrice;
    }

    @Override
    public void setItemTotalPrice(int itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getTitles() {
        return titles;
    }

    @Override
    public void setTitles(String titles) {
        this.titles = titles;
    }

    @Override
    public String getImages() {
        return images;
    }

    @Override
    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String getItemCategory() {
        return itemCategory;
    }

    @Override
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
}
