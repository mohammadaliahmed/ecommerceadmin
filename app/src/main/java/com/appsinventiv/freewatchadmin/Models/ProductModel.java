package com.appsinventiv.freewatchadmin.Models;

/**
 * Created by AliAh on 19/02/2018.
 */

public class ProductModel {
    String id,itemTitle,subTitle,itemDescription,itemSpecification,itemColor,thumbnailUrl,sku,isActive;
    long itemPrice,timeUploaded,itemQuantity;
    int itemCategory;


    public ProductModel(String id, String itemTitle, String subTitle, long itemPrice, String itemDescription, String itemSpecification, String thumbnailUrl,String itemColor , String sku, String isActive, long timeUploaded, long itemQuantity, int itemCategory) {
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
