package com.example.starvelater.jsonmodels;

import java.util.List;

public class AreasModel { /**
     * status : true
     * msg : Data retrieved Successfully
     * data : [{"Area_ID":"5f21cb6143bae","Area_Name":"Jayendra Nagar","City_ID":"5ebeda1838ea8","City_Name":"Kakinada"}]
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
