package com.appsinventiv.freewatchadmin.Models;

/**
 * Created by AliAh on 21/03/2018.
 */

public class ProductItemCount {
    String productId,thumbnailUrl,productTitle;
    int quantity;
    long price;

    public ProductItemCount() {
    }

    public ProductItemCount(String productTitle , String thumbnailUrl, String productId, int quantity, long price) {
        this.productId = productId;
        this.thumbnailUrl = thumbnailUrl;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.price = price;
    }


    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
