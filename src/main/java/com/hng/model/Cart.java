package com.hng.model;

public class Cart {
   private Long id;
   private Long userId;
   private int quantity;

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

   public Cart(Long userId) {
      this.userId = userId;
   }
}
