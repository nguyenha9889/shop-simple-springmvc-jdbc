package com.hng.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
   private Long id;
   private Long userId;
   private String receivedName;
   private String phone;
   private String address;
   private double total;
   private String status;
   private String description;
   private LocalDate orderAt;
   private LocalDate deliverAt;

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

   public LocalDate getOrderAt() {
      return orderAt;
   }

   public void setOrderAt(LocalDate orderAt) {
      this.orderAt = orderAt;
   }

   public LocalDate getDeliverAt() {
      return deliverAt;
   }

   public void setDeliverAt(LocalDate deliverAt) {
      this.deliverAt = deliverAt;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
