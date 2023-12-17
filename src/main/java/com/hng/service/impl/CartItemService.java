package com.hng.service.impl;

import com.hng.dao.ICartItemDao;
import com.hng.dto.request.FormDetail;
import com.hng.model.CartItem;
import com.hng.model.Product;
import com.hng.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartItemService implements ICartItemService {
   @Autowired
   private ICartItemDao cartItemDao;

   @Override
   public List<CartItem> findAll(){
      return cartItemDao.findAll();
   }

   @Override
   public void save(CartItem cartItem) {
      cartItemDao.save(cartItem);
   }

   @Override
   public int delete(CartItem ci) {
      return cartItemDao.delete(ci);
   }

   @Override
   public CartItem findByCartAndProduct(Long cartId, Long productId) {
      return cartItemDao.findByCartAndProduct(cartId, productId);
   }

   @Override
   public List<CartItem> findByCart(Long cartId) {
      return cartItemDao.findByCart(cartId);
   }

   @Override
   public CartItem create(Long cartId, Product p, FormDetail form) {
      CartItem cartItem = findByCartAndProduct(cartId, p.getId());
      if (cartItem == null) {
         cartItem = new CartItem(
               cartId, p.getId(), p.getUnitPrice(), form.getQuantity());
      } else {
         cartItem = findByCartAndProduct(cartId, p.getId());
         cartItem.setQuantity(cartItem.getQuantity() + form.getQuantity());
      }
      return cartItem;
   }
}
