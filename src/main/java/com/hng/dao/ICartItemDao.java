package com.hng.dao;

import com.hng.model.CartItem;

import java.util.List;

public interface ICartItemDao extends IGenericDao<CartItem, Long>{
   int delete(CartItem ci);
   CartItem findByCartAndProduct(Long cartId, Long productId);
   List<CartItem> findByCart(Long cartId);
}
