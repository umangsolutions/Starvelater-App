package com.example.starvelater.jsonmodels;

import java.util.List;

public class RestaurantsModel {


    /**
     * status : true
     * data : [{"Restaurant_ID":"5f24485ec2a92","Type":"Most Popular","RestaurantLogo":"http://starvelater.net/partner/uploads/Barbeque_Nation_New_Logo.jpg","Restaurant_Name":"Barbeque Nation","KnownFor":"Casual Dining - North Indian, BBQ, Beverages","Address":"Connaught Palace","OperationStatus":"Open","AvgPrepTime":"0"},{"Restaurant_ID":"5f246e6497a68","Type":"All Restaurants","RestaurantLogo":"http://starvelater.net/partner/uploads/taj_mahal.jpg","Restaurant_Name":"Taj Mahal Hotel","KnownFor":"North Indian, Continental, Asian","Address":" The Taj Mahal Palace, Near Sai Ranga Theatre","OperationStatus":"Open","AvgPrepTime":"0"},{"Restaurant_ID":"5f251ed34864f","Type":"Most Popular","RestaurantLogo":"http://starvelater.net/partner/uploads/pizzahut.png","Restaurant_Name":"Pizza Hut","KnownFor":"Pizzas, Burgers","Address":"PVR Mall","OperationStatus":"Open","AvgPrepTime":"0"},{"Restaurant_ID":"5f25426e7ad1b","Type":"Most Popular","RestaurantLogo":"http://starvelater.net/partner/uploads/ccd.jpg","Restaurant_Name":"Cafe Coffee Day","KnownFor":"Coffees, Food Beverages","Address":"GMR Nagar","OperationStatus":"Open","AvgPrepTime":"0"}]
     */

    private boolean status;
    private List<DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Restaurant_ID : 5f24485ec2a92
         * Type : Most Popular
         * RestaurantLogo : http://starvelater.net/partner/uploads/Barbeque_Nation_New_Logo.jpg
         * Restaurant_Name : Barbeque Nation
         * KnownFor : Casual Dining - North Indian, BBQ, Beverages
         * Address : Connaught Palace
         * OperationStatus : Open
         * AvgPrepTime : 0
         */

        private String Restaurant_ID;
        private String Type;
        private String RestaurantLogo;
        private String Restaurant_Name;
        private String KnownFor;
        private String Address;
        private String OperationStatus;
        private String AvgPrepTime;

        public String getRestaurant_ID() {
            return Restaurant_ID;
        }

        public void setRestaurant_ID(String Restaurant_ID) {
            this.Restaurant_ID = Restaurant_ID;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getRestaurantLogo() {
            return RestaurantLogo;
        }

        public void setRestaurantLogo(String RestaurantLogo) {
            this.RestaurantLogo = RestaurantLogo;
        }

        public String getRestaurant_Name() {
            return Restaurant_Name;
        }

        public void setRestaurant_Name(String Restaurant_Name) {
            this.Restaurant_Name = Restaurant_Name;
        }

        public String getKnownFor() {
            return KnownFor;
        }

        public void setKnownFor(String KnownFor) {
            this.KnownFor = KnownFor;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getOperationStatus() {
            return OperationStatus;
        }

        public void setOperationStatus(String OperationStatus) {
            this.OperationStatus = OperationStatus;
        }

        public String getAvgPrepTime() {
            return AvgPrepTime;
        }

        public void setAvgPrepTime(String AvgPrepTime) {
            this.AvgPrepTime = AvgPrepTime;
        }
    }
}
