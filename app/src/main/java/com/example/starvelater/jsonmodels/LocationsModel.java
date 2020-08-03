package com.example.starvelater.jsonmodels;

import java.util.List;

public class LocationsModel {

    /**
     * status : true
     * data : [{"Area_ID":"5f21cb6143bae","Area_Name":"Jayendra Nagar","City_ID":"5ebeda1838ea8","City_Name":"Kakinada"},{"Area_ID":"5f22bfb0a9a44","Area_Name":"Nehru Nagar","City_ID":"5ebf40faa69de","City_Name":"Bhilai"},{"Area_ID":"5f22bfe8a3103","Area_Name":"Ghadi Chowk","City_ID":"5ebf40faa69de","City_Name":"Bhilai"},{"Area_ID":"5f244a8262d9a","Area_Name":"Panjagutta","City_ID":"5f244a6243cbe","City_Name":"Hyderabad"},{"Area_ID":"5f244a92ee052","Area_Name":"Jubilee Hills","City_ID":"5f244a6243cbe","City_Name":"Hyderabad"}]
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
         * Area_ID : 5f21cb6143bae
         * Area_Name : Jayendra Nagar
         * City_ID : 5ebeda1838ea8
         * City_Name : Kakinada
         */

        private String Area_ID;
        private String Area_Name;
        private String City_ID;
        private String City_Name;

        public String getArea_ID() {
            return Area_ID;
        }

        public void setArea_ID(String Area_ID) {
            this.Area_ID = Area_ID;
        }

        public String getArea_Name() {
            return Area_Name;
        }

        public void setArea_Name(String Area_Name) {
            this.Area_Name = Area_Name;
        }

        public String getCity_ID() {
            return City_ID;
        }

        public void setCity_ID(String City_ID) {
            this.City_ID = City_ID;
        }

        public String getCity_Name() {
            return City_Name;
        }

        public void setCity_Name(String City_Name) {
            this.City_Name = City_Name;
        }
    }
}
