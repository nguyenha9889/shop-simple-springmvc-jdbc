package com.hng.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
   private Long id;
   private Long userId;
   private String receivedName;
   private String phone;
   private String address;
   private double total;
   private enum status {};
   List<OrderDetail> orderDetails;
   private LocalDateTime orderAt;
   private LocalDateTime deliverAt;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getReceivedName() {
      return receivedName;
   }

   public void setReceivedName(String receivedName) {
      this.receivedName = receivedName;
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

   public double getTotal() {
      return total;
   }

   public void setTotal(double total) {
      this.total = total;
   }

   public List<OrderDetail> getOrderDetails() {
      return orderDetails;
   }

   public void setOrderDetails(List<OrderDetail> orderDetails) {
      this.orderDetails = orderDetails;
   }

   public LocalDateTime getOrderAt() {
      return orderAt;
   }

   public void setOrderAt(LocalDateTime orderAt) {
      this.orderAt = orderAt;
   }

   public LocalDateTime getDeliverAt() {
      return deliverAt;
   }

   public void setDeliverAt(LocalDateTime deliverAt) {
      this.deliverAt = deliverAt;
   }
}
