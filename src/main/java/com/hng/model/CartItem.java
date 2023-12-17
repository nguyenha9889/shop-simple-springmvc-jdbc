package com.hng.model;

import java.time.LocalDate;

public class CartItem {
   private Long cartId;
   private Long productId;
   private double unitPrice;
   private int quantity;
   private LocalDate createdAt;
   private LocalDate updatedAt;
   private LocalDate deletedAt;

   public Long getCartId() {
      return cartId;
   }

   public void setCartId(Long cartId) {
      this.cartId = cartId;
   }

   public Long getProductId() {
      return productId;
   }

   public void setProductId(Long productId) {
      this.productId = productId;
   }

   public double getUnitPrice() {
      return unitPrice;
   }

   public void setUnitPrice(double unitPrice) {
      this.unitPrice = unitPrice;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
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

   public LocalDate getDeletedAt() {
      return deletedAt;
   }

   public void setDeletedAt(LocalDate deletedAt) {
      this.deletedAt = deletedAt;
   }

   public CartItem(Long cartId, Long productId, double unitPrice) {
      this.cartId = cartId;
      this.productId = productId;
      this.unitPrice = unitPrice;
   }

   public CartItem(Long cartId, Long productId, double unitPrice, int quantity) {
      this.cartId = cartId;
      this.productId = productId;
      this.unitPrice = unitPrice;
      this.quantity = quantity;
   }

   public CartItem() {
   }
}
