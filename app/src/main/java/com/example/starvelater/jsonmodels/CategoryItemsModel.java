package com.example.starvelater.jsonmodels;

import java.util.List;

public class CategoryItemsModel {


    /**
     * status : true
     * Restaurant_Name : Paaka
     * data : [{"Category":"Snacks","Item_ID":"5f2a92c2c2b7f","Item_Name":"Punugulu","Type":"Vegetarian","Item_Price":"140"},{"Category":"Snacks","Item_ID":"5f2a93a5b1353","Item_Name":"Mix Veg Pakoda","Type":"Vegetarian","Item_Price":"140"},{"Category":"Snacks","Item_ID":"5f2a943046dbe","Item_Name":"Onion Pakoda","Type":"Vegetarian","Item_Price":"140"},{"Category":"Organic Breakfast","Item_ID":"5f2a94bde83df","Item_Name":"Millet Pongal","Type":"Vegetarian","Item_Price":"120"},{"Category":"Organic Breakfast","Item_ID":"5f2a952743de6","Item_Name":"Poori Bhaji","Type":"Vegetarian","Item_Price":"150"},{"Category":"Organic Breakfast","Item_ID":"5f2a958135176","Item_Name":"Masala Omlette","Type":"Non-Vegetarian","Item_Price":"100"},{"Category":"Plats Du Jour","Item_ID":"5f2a95f40b75e","Item_Name":"Pan Tossed Potatoes","Type":"Vegetarian","Item_Price":"93"},{"Category":"Plats Du Jour","Item_ID":"5f2a96562a6f2","Item_Name":"Ghee Vegetable Pappu Chaaru","Type":"Vegetarian","Item_Price":"53"},{"Category":"Paaka Pulav","Item_ID":"5f2a96b033da1","Item_Name":"Paneer Pulav","Type":"Vegetarian","Item_Price":"263"},{"Category":"Paaka Pulav","Item_ID":"5f2a97516d43d","Item_Name":"Chicken Pulav","Type":"Non-Vegetarian","Item_Price":"228"},{"Category":"Paaka Pulav","Item_ID":"5f2a97f95c2ba","Item_Name":"Mutton Pulav","Type":"Vegetarian","Item_Price":"275"}]
     */

    private boolean status;
    private String Restaurant_Name;
    private List<DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRestaurant_Name() {
        return Restaurant_Name;
    }

    public void setRestaurant_Name(String Restaurant_Name) {
        this.Restaurant_Name = Restaurant_Name;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Category : Snacks
         * Item_ID : 5f2a92c2c2b7f
         * Item_Name : Punugulu
         * Type : Vegetarian
         * Item_Price : 140
         */

        private String Category;
        private String Item_ID;
        private String Item_Name;
        private String Type;
        private String Item_Price;

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public String getItem_ID() {
            return Item_ID;
        }

        public void setItem_ID(String Item_ID) {
            this.Item_ID = Item_ID;
        }

        public String getItem_Name() {
            return Item_Name;
        }

        public void setItem_Name(String Item_Name) {
            this.Item_Name = Item_Name;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getItem_Price() {
            return Item_Price;
        }

        public void setItem_Price(String Item_Price) {
            this.Item_Price = Item_Price;
        }
    }
}
