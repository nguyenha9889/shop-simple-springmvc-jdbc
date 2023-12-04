package com.hng.service;

import com.hng.dto.request.FormProduct;
import com.hng.model.Catalog;
import com.hng.model.Product;
import java.util.List;

public interface IProductService extends IGenericService<Product, Long>{
   List<Product> findAll(int page, int size);
   List<Product> getListByName(String name, int page, int size);
   Product create(FormProduct formProduct);
   boolean checkNameExist(Long id, String name);
   int getTotalPage(List<Product> list, int size);
}
