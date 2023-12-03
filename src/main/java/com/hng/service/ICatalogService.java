package com.hng.service;

import com.hng.model.Catalog;

import java.util.List;

public interface ICatalogService extends IGenericService<Catalog, Long>{
   List<Catalog> findAll(int page, int size);
   List<Catalog> getListByName(String name);
   Catalog findByName(String name);
   int getTotalPage(int size);
}
