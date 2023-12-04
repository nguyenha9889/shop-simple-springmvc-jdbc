package com.hng.model;

import java.time.LocalDate;


public class Product {
   private Long id;
   private String name;
   private Long categoryId;
   private String description;
   private String imagePath;
   private double unitPrice;
   private int stock;
   private LocalDate createdAt;
   private LocalDate updatedAt;
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

   public String getImagePath() {
      return imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
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

   public LocalDate getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDate createdAt) {
      this.createdAt = createdAt;
   }

   public LocalDate getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDate updatedAt) {
      this.updatedAt = updatedAt;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public Product() {
   }

   public Product(Long id, String name, Long categoryId, String description, String imagePath, double unitPrice, int stock, boolean status) {
      this.id = id;
      this.name = name;
      this.categoryId = categoryId;
      this.description = description;
      this.imagePath = imagePath;
      this.unitPrice = unitPrice;
      this.stock = stock;
      this.status = status;
   }
}
