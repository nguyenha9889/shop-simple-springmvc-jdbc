package com.hng.model;

import java.util.List;

public class User {
   private Long id;
   private String fullName;
   private String username;
   private String email;
   private String password;
   private String phone;
   private String imagePath;
   private boolean role;
   private boolean status;
   private List<OrderDetail> cart;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getImagePath() {
      return imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   public boolean isRole() {
      return role;
   }

   public void setRole(boolean role) {
      this.role = role;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public List<OrderDetail> getCart() {
      return cart;
   }

   public void setCart(List<OrderDetail> cart) {
      this.cart = cart;
   }

   public User() {
   }

   public User(String fullName, String username, String phone, String email, String password) {
      this.fullName = fullName;
      this.username = username;
      this.phone = phone;
      this.email = email;
      this.password = password;
   }
}
