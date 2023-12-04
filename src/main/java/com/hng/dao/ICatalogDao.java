package com.hng.dao;

import com.hng.model.Catalog;
import java.util.List;

public interface ICatalogDao extends IGenericDao<Catalog, Long>{
   List<Catalog> findAll(int limit, int offset);
   List<Catalog> getListByName(String name);
}
