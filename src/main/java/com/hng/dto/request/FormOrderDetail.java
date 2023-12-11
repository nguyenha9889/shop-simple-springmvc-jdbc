package com.hng.dto.request;

public class FormOrderDetail {
   private Long id;
   private Long productId;
   private String productName;
   private double unitPrice;
   private int quantity;
   private String imagePath;
   private String description;

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

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public String getImagePath() {
      return imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public FormOrderDetail() {
   }

   public FormOrderDetail(Long productId,String productName, double unitPrice, String imagePath, String description) {
      this.productId = productId;
      this.productName = productName;
      this.unitPrice = unitPrice;
      this.imagePath = imagePath;
      this.description = description;
   }
}
