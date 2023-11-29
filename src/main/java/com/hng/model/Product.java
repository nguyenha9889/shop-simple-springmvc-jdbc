package com.hng.model;

import java.time.LocalDateTime;

public class Product {
   private Long id;
   private String name;
   private Long categoryId;
   private String description;
   private double unitPrice;
   private int stock;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   private boolean status;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Long getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public double getUnitPrice() {
      return unitPrice;
   }

   public void setUnitPrice(double unitPrice) {
      this.unitPrice = unitPrice;
   }

   public int getStock() {
      return stock;
   }

   public void setStock(int stock) {
      this.stock = stock;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public LocalDateTime getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }
}
