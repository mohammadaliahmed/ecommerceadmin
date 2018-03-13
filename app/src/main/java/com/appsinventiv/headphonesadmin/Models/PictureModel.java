package com.appsinventiv.headphonesadmin.Models;

/**
 * Created by AliAh on 01/03/2018.
 */

public class PictureModel {
    String url;
    int number;

    public PictureModel() {
    }

    public PictureModel(String url, int number) {
        this.url = url;
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
