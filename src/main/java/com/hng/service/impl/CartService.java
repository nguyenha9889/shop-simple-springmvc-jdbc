package com.hng.service.impl;

import com.hng.dao.ICartDao;
import com.hng.model.Cart;
import com.hng.model.CartItem;
import com.hng.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
   @Autowired
   private ICartDao cartDao;

   @Override
   public void save(Cart cart) {
      cartDao.save(cart);
   }

   @Override
   public Cart findCartByUserId(Long userId) {
      return cartDao.findCartByUserId(userId);
   }

   @Override
   public double getTotal(List<CartItem> cartItems) {
      if (cartItems.isEmpty()){
         return 0;
      }
      double total = 0;
      for (CartItem ci: cartItems) {
         double result = ci.getQuantity() * ci.getUnitPrice();
         total += result;
      }
      return total;
   }

}
