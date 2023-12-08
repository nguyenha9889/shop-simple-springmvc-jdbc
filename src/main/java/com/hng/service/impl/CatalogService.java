package com.hng.service.impl;

import com.hng.dao.ICatalogDao;
import com.hng.dto.request.FormCatalog;
import com.hng.model.Catalog;
import com.hng.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;


@Service
public class CatalogService implements ICatalogService {

   @Autowired
   private ICatalogDao catalogDao;

   @Override
   public List<Catalog> findAll() {
      return catalogDao.findAll();
   }

   @Override
   public List<Catalog> findAll(int page, int size) {
      return catalogDao.findAll(size, size*page);
   }

   @Override
   public int getTotalPage(List<Catalog> list,int size) {
      int count  = list.size();
      if (count%size==0){
         return count/size;
      }
      return count/size+1;
   }

   @Override
   public List<Catalog> getListByName(String name, int page, int size) {
      return catalogDao.getListByName(name, size, size*page);
   }

   /**
    * Lấy danh sách category có sản phẩm
    * @return CatalogList
    */
   @Override
   public List<Catalog> getListHaveProduct() {
      return catalogDao.getListHaveProduct();
   }

   @Override
   public Catalog create(FormCatalog formCatalog) {
      return new Catalog(
            formCatalog.getId(),
            formCatalog.getName(),
            formCatalog.getDescription(),
            formCatalog.isStatus()
      );
   }

   @Override
   public boolean checkNameExist(Long id, String name) {
      for (Catalog c: catalogDao.findAll()) {
         if (c.getName().equalsIgnoreCase(name.trim())) {
            return !Objects.equals(c.getId(), id);
         }
      }
      return false;
   }

   @Override
   public Catalog findById(Long id) {
      return catalogDao.findById(id);
   }

   @Override
   public void save(Catalog catalog) {
      catalogDao.save(catalog);
   }

   @Override
   public int delete(Long id) {
      return catalogDao.delete(id);
   }
}


