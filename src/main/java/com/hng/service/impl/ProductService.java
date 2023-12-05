package com.hng.service.impl;

import com.hng.dao.IProductDao;
import com.hng.dto.request.FormProduct;
import com.hng.model.Product;
import com.hng.service.IProductService;
import com.hng.service.FirebaseUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;


@Service
public class ProductService implements IProductService {
   @Autowired
   private IProductDao productDao;
   @Autowired
   private FirebaseUploadService firebaseService;
   @Override
   public List<Product> findAll() {
      return productDao.findAll();
   }

   @Override
   public List<Product> findAll(int page, int size) {
      return productDao.findAll(size, size*page);
   }

   @Override
   public int getTotalPage(List<Product> list, int size) {
      int count  = list.size();
      if (count%size==0){
         return count/size;
      }
      return count/size+1;
   }

   @Override
   public Product findById(Long id) {
      return productDao.findById(id);
   }

   @Override
   public void save(Product product) {
      productDao.save(product);
   }

   // set status false
   @Override
   public int delete(Long id) {
      return productDao.delete(id);
   }

   @Override
   public List<Product> getListByName(String name, int page, int size) {
      return productDao.getListByName(name, page, size);
   }

   /**
    * Phương thức convert FormProduct sang Product
    * @param formProduct FormProduct (ProductRequest DTO) object
    * @return Product
    */
   @Override
   public Product create(FormProduct formProduct) {
      String pathImage = firebaseService.uploadFileToFirebase(formProduct.getImage());

      return new Product(
            formProduct.getId(),
            formProduct.getName(),
            formProduct.getCategoryId(),
            formProduct.getDescription(),
            pathImage,
            formProduct.getUnitPrice(),
            formProduct.isStatus()
      );
   }

   @Override
   public boolean checkNameExist(Long id, String name) {
      for (Product p: productDao.findAll()) {
         if (p.getName().equalsIgnoreCase(name)) {
            return !Objects.equals(p.getId(), id);
         }
      }
      return false;
   }
}
