package com.hng.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class FormProduct {
   private Long id;
   private String name;
   private Long categoryId;
   private String description;
   private MultipartFile image;
   private double unitPrice;
   private int stock;

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

   public MultipartFile getImage() {
      return image;
   }

   public void setImage(MultipartFile image) {
      this.image = image;
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
}
