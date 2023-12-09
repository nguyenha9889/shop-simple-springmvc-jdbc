package com.hng.service.impl;

import com.hng.model.Cart;
import com.hng.service.ICartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {

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

   }

   @Override
   public int delete(Long id) {
      return 0;
   }
}
