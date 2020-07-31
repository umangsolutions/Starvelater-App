package com.example.starvelater.jsonmodels;

import java.util.List;

public class PopularRestaurantsModel {

    /**
     * status : true
     * msg : Most Popular Records Loaded Successfully
     * data : [{"Restaurant_ID":"5f244bb780f1f","RestaurantLogo":"http://starvelater.net/partner/uploads/paaka.jpg","Restaurant_Name":"Paaka","Address":"SRMT Mall ","AvgPrepTime":"0"},{"Restaurant_ID":"5f244fed59afe","RestaurantLogo":"http://starvelater.net/partner/uploads/download.png","Restaurant_Name":"Bombay Kulfi","Address":"IMAX Cafe","AvgPrepTime":"0"},{"Restaurant_ID":"5f244eea04ff2","RestaurantLogo":"http://starvelater.net/partner/uploads/subayya.jpg","Restaurant_Name":"Subbaya Gari Hotel","Address":"Forum Sujana Mall","AvgPrepTime":"0"}]
     */

    private boolean status;
    private String msg;
    private List<DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Restaurant_ID : 5f244bb780f1f
         * RestaurantLogo : http://starvelater.net/partner/uploads/paaka.jpg
         * Restaurant_Name : Paaka
         * Address : SRMT Mall
         * AvgPrepTime : 0
         */

        private String Restaurant_ID;
        private String RestaurantLogo;
        private String Restaurant_Name;
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
