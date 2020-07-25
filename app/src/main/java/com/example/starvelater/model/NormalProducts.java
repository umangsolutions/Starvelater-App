package com.example.starvelater.model;

public class NormalProducts {

    private int unitPrice;
    private int itemTotalPrice;
    private int quantity;
    private String titles;

    public NormalProducts()
    {

    }

    public NormalProducts(int unitPrice, int itemTotalPrice, int quantity, String titles) {
        this.unitPrice = unitPrice;
        this.itemTotalPrice = itemTotalPrice;
        this.quantity = quantity;
        this.titles = titles;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(int itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }
}
