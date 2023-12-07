package com.hng.dao;

import com.hng.model.Product;
import java.util.List;

public interface IProductDao extends IGenericDao<Product, Long>{
   List<Product> findAll(int limit, int offset);
   List<Product> getListByName(String name, int limit, int offset);
   List<Product> getListByCateId(Long cateId, int limit, int offset);
}
