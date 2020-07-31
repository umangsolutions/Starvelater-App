package com.example.starvelater.jsonmodels;

import java.util.List;

public class CitiesModel {


    /**
     * status : true
     * data : [{"City_ID":"5ebeda1838ea8","City_Name":"Kakinada"},{"City_ID":"5ebedb532bef4","City_Name":"Mumbai"},{"City_ID":"5ebf40ca597bb","City_Name":"Rajahmundry"},{"City_ID":"5ebf40faa69de","City_Name":"Bhilai"},{"City_ID":"5ebf414f4829c","City_Name":"Berhampur"},{"City_ID":"5ec127391a319","City_Name":"Chennai"},{"City_ID":"5ec13085e3877","City_Name":"Tiruvananthapuram"},{"City_ID":"5ec7880201c66","City_Name":"Dispur"},{"City_ID":"5ed78d05af42e","City_Name":"Panaji"},{"City_ID":"5f21999194d98","City_Name":"Bhimavaram"},{"City_ID":"5f21bb37e88f0","City_Name":"Tuni"}]
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
         * City_ID : 5ebeda1838ea8
         * City_Name : Kakinada
         */

        private String City_ID;
        private String City_Name;

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
