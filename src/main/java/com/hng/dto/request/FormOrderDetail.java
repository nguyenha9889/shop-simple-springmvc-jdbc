package com.hng.dto.request;

public class FormOrderDetail {
   private Long id;
   private Long productId;
   private int quantity;

   public int getQuantity() {
      return quantity;
   }
   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getProductId() {
      return productId;
   }

   public void setProductId(Long productId) {
      this.productId = productId;
   }

   public FormOrderDetail() {
   }

   public FormOrderDetail(Long productId, int quantity) {
      this.productId = productId;
      this.quantity = quantity;
   }
}
