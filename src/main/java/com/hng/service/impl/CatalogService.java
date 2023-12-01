package com.hng.service.impl;

import com.hng.dao.ICatalogDao;
import com.hng.model.Catalog;
import com.hng.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService implements ICatalogService {
   @Autowired
   private ICatalogDao catalogDao;
   @Override
   public List<Catalog> findAll(int limit, int offset) {
      return catalogDao.findAll(limit, offset);
   }

   @Override
   public List<Catalog> getListByName(String name) {
      return catalogDao.getListByName(name);
   }

   @Override
   public Catalog findByName(String name) {
      List<Catalog> list = catalogDao.findAll();
      return list.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
   }

   @Override
   public List<Catalog> findAll() {
      return catalogDao.findAll();
   }

   @Override
   public Catalog findById(Long id) {
      return catalogDao.findById(id);
   }

   @Override
   public void save(Catalog catalog) {
      catalogDao.save(catalog);
   }

   // Set status false
   @Override
   public void delete(Long id) {
      catalogDao.delete(id);
   }
}
