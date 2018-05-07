package com.appsinventiv.shoesadmin.Models;

import java.util.ArrayList;

/**
 * Created by AliAh on 26/02/2018.
 */

public class OrderModel {
    String orderId,userId,fullName,email,phone,address,city,orderStatus;
    long time,totalPrice,itemCount;
    ArrayList<ProductItemCount> productIdList;

    public OrderModel() {
    }

    public OrderModel(String orderId, String userId, String fullName, String email, String phone, String address, String city, String orderStatus, long time, long totalPrice, long itemCount, ArrayList<ProductItemCount> productIdList) {
        this.orderId = orderId;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.orderStatus = orderStatus;
        this.time = time;
        this.totalPrice = totalPrice;
        this.itemCount = itemCount;
        this.productIdList = productIdList;
    }

    public ArrayList<ProductItemCount> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(ArrayList<ProductItemCount> productIdList) {
        this.productIdList = productIdList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }
}
