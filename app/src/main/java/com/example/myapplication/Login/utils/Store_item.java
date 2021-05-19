package com.example.myapplication.Login.utils;

import java.io.Serializable;

public class Store_item implements Serializable {
    public Store_item(String img , String name , String price , String type){
        this.img_url = img;
        this.skin_name = name;
        this.skin_price = price;
        this.skin_type = type;
    }
    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSkin_name() {
        return skin_name;
    }

    public void setSkin_name(String skin_name) {
        this.skin_name = skin_name;
    }

    public String getSkin_price() {
        return skin_price;
    }

    public void setSkin_price(String skin_price) {
        this.skin_price = skin_price;
    }

    public String getSkin_type() {
        return skin_type;
    }

    public void setSkin_type(String skin_type) {
        this.skin_type = skin_type;
    }

    private String img_url;
    private String skin_name;
    private String skin_price;
    private String skin_type;
}
