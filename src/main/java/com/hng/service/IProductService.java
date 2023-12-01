package com.hng.service;

import com.hng.model.Product;
import java.util.List;

public interface IProductService extends IGenericService<Product, Long>{
   List<Product> findAll(int limit, int offset);
   List<Product> getListByName(String name);

   boolean checkExistByName(String name);
}
