package com.hng.dto.request;

public class FormOrder {
   private String receivedName;
   private String address;
   private String phone;
   private String description;
   private double total;

   public String getReceivedName() {
      return receivedName;
   }

   public void setReceivedName(String receivedName) {
      this.receivedName = receivedName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public double getTotal() {
      return total;
   }

   public void setTotal(double total) {
      this.total = total;
   }
}
