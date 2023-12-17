package com.hng.service;

import com.hng.dto.request.FormDetail;
import com.hng.model.CartItem;
import com.hng.model.Product;

import java.util.List;

public interface ICartItemService extends IGenericService<CartItem, Long> {
   int delete(CartItem ci);
   CartItem findByCartAndProduct(Long cartId, Long productId);
   List<CartItem> findByCart(Long cartId);
   CartItem create(Long cartId, Product p, FormDetail form);
}
