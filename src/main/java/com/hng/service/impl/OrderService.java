package com.hng.service.impl;

import com.hng.dao.IOrderDao;
import com.hng.model.Order;
import com.hng.model.User;
import com.hng.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
   @Autowired
   private IOrderDao orderDao;
   @Override
   public List<Order> findAll() {
      return null;
   }

   @Override
   public Order findById(Long id) {
      return null;
   }

   @Override
   public int save(Order order) {
      return orderDao.save(order);
   }

   @Override
   public int delete(Long id) {
      return 0;
   }

   @Override
   public Order create(User user) {
      Order order = new Order();
      order.setReceivedName(user.getFullName());

      return order;
   }
}
