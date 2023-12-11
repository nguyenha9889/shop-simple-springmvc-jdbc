package com.hng.model;

public class Cart {
   private Long id;
   private Long userId;
   private int total;

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

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public Cart() {
   }

   public Cart(Long userId, int total) {
      this.userId = userId;
      this.total = total;
   }
}
