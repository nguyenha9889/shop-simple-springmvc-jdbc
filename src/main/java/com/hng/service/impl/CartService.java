package com.hng.service.impl;

import com.hng.dao.ICartDao;
import com.hng.model.Cart;
import com.hng.model.OrderDetail;
import com.hng.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
   @Autowired
   private ICartDao cartDao;

   @Override
   public List<Cart> findAll() {
      return null;
   }

   @Override
   public Cart findById(Long id) {
      return null;
   }

   @Override
   public void save(Cart cart) {
      cartDao.save(cart);
   }

   @Override
   public int delete(Long id) {
      return 0;
   }

   @Override
   public Cart create(Long userId, List<OrderDetail> orderDetails) {
      Cart cart =  new Cart();
      cart.setUserId(userId);
      double total = 0.0;
      for (OrderDetail or: orderDetails) {
         double result = or.getUnitPrice() * or.getQuantity();
         total += result;
      }
      cart.setTotal(total);
      return cart;
   }
}
