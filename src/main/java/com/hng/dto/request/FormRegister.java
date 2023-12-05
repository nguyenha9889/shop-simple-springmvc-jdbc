package com.hng.dto.request;

public class FormRegister {
   private Long id;
   private String fullName;
   private String username;
   private String email;
   private String password;
   private String rePassword;
   private String phone;
   private boolean role;
   private boolean status;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
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

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getRePassword() {
      return rePassword;
   }

   public void setRePassword(String rePassword) {
      this.rePassword = rePassword;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
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

   public FormRegister() {
   }

   public FormRegister(Long id, String fullName, String username, String email, String password, String phone, boolean role, boolean status) {
      this.id = id;
      this.fullName = fullName;
      this.username = username;
      this.email = email;
      this.password = password;
      this.phone = phone;
      this.role = role;
      this.status = status;
   }
}
