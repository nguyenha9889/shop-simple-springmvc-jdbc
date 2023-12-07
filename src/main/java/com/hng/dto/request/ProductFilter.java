package com.hng.dto.request;

import java.time.LocalDate;

public class ProductFilter {
   private Long categoryId;
   private String category;
   private double unitPrice;
   private LocalDate updatedAt;

   public Long getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public double getUnitPrice() {
      return unitPrice;
   }

   public void setUnitPrice(double unitPrice) {
      this.unitPrice = unitPrice;
   }

   public LocalDate getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDate updatedAt) {
      this.updatedAt = updatedAt;
   }

   public ProductFilter() {
   }

   public ProductFilter(Long categoryId, String category, double unitPrice, LocalDate updatedAt) {
      this.categoryId = categoryId;
      this.category = category;
      this.unitPrice = unitPrice;
      this.updatedAt = updatedAt;
   }
}
