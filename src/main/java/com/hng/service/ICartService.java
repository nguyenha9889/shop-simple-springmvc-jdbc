package com.hng.service;


import com.hng.model.Cart;
import com.hng.model.OrderDetail;

import java.util.List;


public interface ICartService extends IGenericService<Cart, Long> {
   Cart findCartByUserId(Long userId);
}
