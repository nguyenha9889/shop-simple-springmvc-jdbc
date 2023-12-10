package com.hng.dto.request;

public class FormOrderDetail {
   private Long id;
   private Long productId;
   private String productName;
   private String productImage;
   private String description;
   private double unitPrice;
   private int quantity;

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

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public double getUnitPrice() {
      return unitPrice;
   }

   public void setUnitPrice(double unitPrice) {
      this.unitPrice = unitPrice;
   }

   public String getProductImage() {
      return productImage;
   }

   public void setProductImage(String productImage) {
      this.productImage = productImage;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public FormOrderDetail() {
   }

   public FormOrderDetail(Long productId, String productName, double unitPrice, String productImage, String description) {
      this.productId = productId;
      this.productName = productName;
      this.unitPrice = unitPrice;
      this.productImage = productImage;
      this.description = description;
   }
}
