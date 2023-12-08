package com.hng.dto.request;


public class ProductFilter {
   private Long categoryId;
   private String productName;

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   // filter sắp xếp theo giá
   private String sortByPrice;

   // Filter sắp xếp theo category
   private String cateName;

   public String getSortByPrice() {
      return sortByPrice;
   }

   public void setSortByPrice(String sortByPrice) {
      this.sortByPrice = sortByPrice;
   }

   public String getCateName() {
      return cateName;
   }

   public void setCateName(String cateName) {
      this.cateName = cateName;
   }

   public Long getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
   }

   public ProductFilter() {
   }

   public ProductFilter(String productName, Long categoryId, String cateName) {
      this.productName = productName;
      this.categoryId = categoryId;
      this.cateName = cateName;
   }
}
