package com.hng.model;


public class OrderDetail {
   private Long productId;
   private Long orderId;
   private String name;
   private double unitPrice;
   private int quantity;

   public Long getProductId() {
      return productId;
   }

   public void setProductId(Long productId) {
      this.productId = productId;
   }

   public Long getOrderId() {
      return orderId;
   }

   public void setOrderId(Long orderId) {
      this.orderId = orderId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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
}
