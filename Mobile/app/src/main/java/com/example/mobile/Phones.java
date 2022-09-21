package com.example.mobile;

import java.io.Serializable;

public class Phones implements Serializable//serialaizable is used to move objects
         {
    ///Variables///
    private String phoneName;
    private double phonePrice;
    private String phoneImg;

             public Phones() {//empty constructor for firebase in main activity query
             }

             ///Constructor///
    public Phones(String phoneName, double phonePrice, String phoneImg) {
        this.phoneName = phoneName;
        this.phonePrice = phonePrice;
        this.phoneImg = phoneImg;

    }

    ///Setters & Getters///
    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public double getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(double phonePrice) {
        this.phonePrice = phonePrice;
    }

    public String getPhoneImg() {
        return phoneImg;
    }

    public void setPhoneImg(String phoneImg) {
        this.phoneImg = phoneImg;
    }
}
