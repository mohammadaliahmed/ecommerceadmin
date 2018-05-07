package com.appsinventiv.shoesadmin.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AliAh on 19/02/2018.
 */

public class ProductModel {
    String id,itemTitle,subTitle,itemDescription,itemSpecification,itemColor,thumbnailUrl,sku,isActive;
    long itemPrice,timeUploaded,itemQuantity;
    int itemCategory;
    List<String> sizesAvailable;
    int ratings;
    int soldQuantity;


    public ProductModel(String id,
                        String itemTitle,
                        String subTitle,
                        String itemDescription,
                        String itemSpecification,
                        String itemColor,
                        String thumbnailUrl,
                        String sku,
                        String isActive,
                        long itemPrice,
                        long timeUploaded,
                        long itemQuantity,
                        int itemCategory,
                        List<String> sizesAvailable,
                        int ratings,
                        int soldQuantity) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.subTitle = subTitle;
        this.itemDescription = itemDescription;
        this.itemSpecification = itemSpecification;
        this.itemColor = itemColor;
        this.thumbnailUrl = thumbnailUrl;
        this.sku = sku;
        this.isActive = isActive;
        this.itemPrice = itemPrice;
        this.timeUploaded = timeUploaded;
        this.itemQuantity = itemQuantity;
        this.itemCategory = itemCategory;
        this.sizesAvailable = sizesAvailable;
        this.ratings = ratings;
        this.soldQuantity = soldQuantity;
    }

    public List<String> getSizesAvailable() {
        return sizesAvailable;
    }

    public void setSizesAvailable(List<String> sizesAvailable) {
        this.sizesAvailable = sizesAvailable;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public ProductModel() {
    }

    public int getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(int itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemSpecification() {
        return itemSpecification;
    }

    public void setItemSpecification(String itemSpecification) {
        this.itemSpecification = itemSpecification;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public long getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(long timeUploaded) {
        this.timeUploaded = timeUploaded;
    }


    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}