package com.hng.service.impl;

import com.hng.dao.ICatalogDao;
import com.hng.dao.IProductDao;
import com.hng.dto.request.FormProduct;
import com.hng.dto.request.ProductFilter;
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
   private ICatalogDao catalogDao;
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

   /**
    * Phương thức tìm kiếm sản phẩm theo các điều kiện có phân trang kết quả
    */
   @Override
   public List<Product> getListByCateIdPaging(ProductFilter filter, int page, int size) {
      return productDao.getListByCateIdPaging(filter, size, size*page);
   }

   /**
    * Phương thức lấy danh sách category có sản phẩm
    * @return List nếu tìm thấy
    */
   @Override
   public List<Product> getListByCateId(ProductFilter filter) {
      return productDao.getListByCateId(filter);
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
      return productDao.getListByName(name, size, size*page);
   }

   /**
    * Phương thức convert FormProduct sang Product
    * @param formProduct FormProduct (ProductRequest DTO) object
    * @return Product
    */
   @Override
   public Product create(FormProduct formProduct) {
      String imagePath = null;
      if (formProduct.getId() != null && formProduct.getImage().getSize() == 0L) {
         imagePath = formProduct.getImagePath();
      } else {
         imagePath = firebaseService.uploadFile(formProduct.getImage());
      }

      return new Product(
            formProduct.getId(),
            formProduct.getName(),
            formProduct.getCategoryId(),
            formProduct.getDescription(),
            imagePath,
            formProduct.getUnitPrice(),
            formProduct.isStatus()
      );
   }

   @Override
   public boolean checkNameExist(Long id, String name) {
      for (Product p: productDao.findAll()) {
         if (p.getName().equalsIgnoreCase(name.trim())) {
            return !Objects.equals(p.getId(), id);
         }
      }
      return false;
   }
}
