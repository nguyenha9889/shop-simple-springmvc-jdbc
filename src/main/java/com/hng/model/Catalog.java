package com.hng.model;

public class Catalog {
   private Long id;
   private String name;
   private String description;
   private String imagePath;
   private boolean status;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public String getImagePath() {
      return imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   public Catalog() {
   }

   public Catalog(Long id, String name, String description, boolean status) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.status = status;
   }
}
