package com.hng.service;

import com.hng.dto.request.FormCatalog;
import com.hng.model.Catalog;

import java.util.List;

public interface ICatalogService extends IGenericService<Catalog, Long>{
   List<Catalog> findAll(int page, int size);
   List<Catalog> getListByName(String name, int page, int size);
   List<Catalog> getListHaveProduct();
   Catalog create(FormCatalog formCatalog);
   boolean checkNameExist(Long id, String name);
   int getTotalPage(List<Catalog> list, int size);
}
