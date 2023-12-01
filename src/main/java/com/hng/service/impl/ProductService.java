package com.hng.service.impl;

import com.hng.dao.IProductDao;
import com.hng.model.Product;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService implements IProductService {
   @Autowired
   private IProductDao productDao;
   @Override
   public List<Product> findAll() {
      return productDao.findAll();
   }

   @Override
   public Product findById(Long id) {
      return productDao.findById(id);
   }

   @Override
   public void save(Product product) {
      productDao.save(product);
   }

   // Set status false
   @Override
   public void delete(Long id) {
      productDao.delete(id);
   }

   @Override
   public List<Product> findAll(int limit, int offset) {
      return productDao.findAll(limit, offset);
   }

   @Override
   public List<Product> getListByName(String name) {
      return productDao.getListByName(name);
   }

   @Override
   public boolean checkExistByName(String name) {
      List<Product> list = getListByName(name);
      if (list.isEmpty()) {
         return false;
      } else {
         return list.stream().anyMatch(p -> p.getName().equalsIgnoreCase(name));
      }
   }
}
