package com.appsinventiv.ecommerceadmin.Models;

/**
 * Created by AliAh on 19/02/2018.
 */

public class ProductModel {
    String id,itemTitle,subTitle,itemDescription,itemSpecification,itemColor,thumbnailUrl,sku,isActive;
    long itemPrice,timeUploaded,itemQuantity;
    float itemWeight;



    public ProductModel() {
    }

    public ProductModel(String id, String itemTitle,String subTitle,long itemPrice, String itemDescription,String itemSpecification,String thumbnailUrl , float itemWeight,  String itemColor, long itemQuantity, String sku, String isActive, long timeUploaded) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemColor = itemColor;
        this.itemQuantity = itemQuantity;
        this.sku = sku;
        this.isActive = isActive;
        this.itemPrice = itemPrice;
        this.timeUploaded = timeUploaded;
        this.itemWeight = itemWeight;
        this.thumbnailUrl=thumbnailUrl;
        this.subTitle=subTitle;
        this.itemSpecification=itemSpecification;
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

    public float getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(float itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id='" + id + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemColor='" + itemColor + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", sku='" + sku + '\'' +
                ", isActive='" + isActive + '\'' +
                ", itemPrice=" + itemPrice +
                ", timeUploaded=" + timeUploaded +
                ", itemQuantity=" + itemQuantity +
                ", itemWeight=" + itemWeight +
                '}';
    }
}
