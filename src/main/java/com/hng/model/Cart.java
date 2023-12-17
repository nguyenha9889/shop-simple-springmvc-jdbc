package com.hng.model;

public class Cart {
   private Long id;
   private Long userId;
   private int quantity;
   private double total;

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public double getTotal() {
      return total;
   }

   public void setTotal(double total) {
      this.total = total;
   }

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

   public Cart() {
   }

   public Cart(Long id, Long userId, int quantity, double total) {
      this.id = id;
      this.userId = userId;
      this.quantity = quantity;
      this.total = total;
   }
}
