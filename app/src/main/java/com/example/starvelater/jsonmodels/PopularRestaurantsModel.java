package com.example.starvelater.jsonmodels;

import java.util.List;

import retrofit2.http.POST;

public class PopularRestaurantsModel {
    /**
     * status : true
     * data : [{"Restaurant_ID":"5f22b510f2edc","RestaurantLogo":"http://starvelater.net/partner/uploads/bill.png","Restaurant_Name":"Koushik Cafe","KnownFor":"Bihar Foods","Address":"70-4-8/32","AvgPrepTime":"0"},{"Restaurant_ID":"5f22bde43ba92","RestaurantLogo":"http://starvelater.net/partner/uploads/HelpSupportWeb.png","Restaurant_Name":"Ram Foods","KnownFor":"Good Foods","Address":"GMRIT","AvgPrepTime":"0"}]
     */

    private boolean status;
    private List<PopularRestaurantsModel.DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<PopularRestaurantsModel.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Restaurant_ID : 5f22b510f2edc
         * RestaurantLogo : http://starvelater.net/partner/uploads/bill.png
         * Restaurant_Name : Koushik Cafe
         * KnownFor : Bihar Foods
         * Address : 70-4-8/32
         * AvgPrepTime : 0
         */

        private String Restaurant_ID;
        private String RestaurantLogo;
        private String Restaurant_Name;
        private String KnownFor;
        private String Address;
        private String AvgPrepTime;

        public String getRestaurant_ID() {
            return Restaurant_ID;
        }

        public void setRestaurant_ID(String Restaurant_ID) {
            this.Restaurant_ID = Restaurant_ID;
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

        public String getAvgPrepTime() {
            return AvgPrepTime;
        }

        public void setAvgPrepTime(String AvgPrepTime) {
            this.AvgPrepTime = AvgPrepTime;
        }
    }
}
